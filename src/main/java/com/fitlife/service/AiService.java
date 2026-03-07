package com.fitlife.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitlife.dto.AiWorkoutRequest;
import com.fitlife.entity.AiWorkoutPlan;
import com.fitlife.entity.HealthMetric;
import com.fitlife.entity.Member;
import com.fitlife.repository.AiPlanRepository;
import com.fitlife.repository.HealthMetricRepository;
import com.fitlife.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiService {

    private final MemberRepository memberRepository;
    private final HealthMetricRepository healthMetricRepository;
    private final AiPlanRepository aiPlanRepository; // Đảm bảo cái này đã được tiêm (Inject)
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${gemini.api-key}")
    private String geminiApiKey;

    @Value("${gemini.api-url}")
    private String geminiApiUrl;

    @Transactional // Đảm bảo việc lưu vào DB được commit an toàn
    public JsonNode generateWorkoutPlan(AiWorkoutRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hội viên"));

        HealthMetric latestMetric = healthMetricRepository.findFirstByMemberOrderByRecordedDateDesc(member)
                .orElseThrow(() -> new RuntimeException("Hội viên chưa đo BMI."));

        String prompt = buildPrompt(member, latestMetric, request);

        // Payload tối giản
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> parts = new HashMap<>();
        parts.put("text", prompt);
        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(parts));
        requestBody.put("contents", List.of(content));

        Map<String, Object> config = new HashMap<>();
        config.put("temperature", 0.2);
        requestBody.put("generationConfig", config);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        String fullUrl = geminiApiUrl + "?key=" + geminiApiKey;

        try {
            log.info("===> Đang gọi Gemini AI cho: {}", member.getFullName());
            ResponseEntity<String> response = restTemplate.postForEntity(fullUrl, entity, String.class);

            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String aiResponseText = rootNode.at("/candidates/0/content/parts/0/text").asText();

            // Làm sạch JSON
            String cleanJson = extractJson(aiResponseText);

            // --- ĐOẠN CODE QUAN TRỌNG NHẤT: LƯU VÀO DB ---
            log.info("===> Đang thực hiện lưu phác đồ vào Database...");
            AiWorkoutPlan planEntity = AiWorkoutPlan.builder()
                    .member(member)
                    .goal(request.getGoal())
                    .planData(cleanJson) // Lưu nguyên cục JSON string
                    .createdAt(LocalDateTime.now())
                    .build();

            AiWorkoutPlan saved = aiPlanRepository.save(planEntity);
            log.info("===> LƯU THÀNH CÔNG! ID bản ghi mới: {}", saved.getId());

            return objectMapper.readTree(cleanJson);

        } catch (Exception e) {
            log.error("Lỗi AI: {}", e.getMessage());
            throw new RuntimeException("Huấn luyện viên AI đang bận, thử lại sau.");
        }
    }

    private String extractJson(String text) {
        String clean = text.trim();
        if (clean.contains("```json")) {
            clean = clean.substring(clean.indexOf("```json") + 7);
            clean = clean.substring(0, clean.lastIndexOf("```"));
        } else if (clean.contains("```")) {
            clean = clean.substring(clean.indexOf("```") + 3);
            clean = clean.substring(0, clean.lastIndexOf("```"));
        }
        return clean.trim();
    }

    private String buildPrompt(Member member, HealthMetric metric, AiWorkoutRequest req) {
        return "Bạn là PT chuyên nghiệp. Hãy tạo phác đồ tập luyện và dinh dưỡng cho khách hàng " + member.getFullName() +
                ". BMI: " + metric.getBmi() + ". Mục tiêu: " + req.getGoal() +
                ". Trình độ: " + req.getFitnessLevel() + ". " +
                "Yêu cầu trả về DUY NHẤT 1 khối JSON chuẩn, không giải thích thêm.";
    }
}