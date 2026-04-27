package com.fitlife.ai.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fitlife.ai.service.AiService;
import com.fitlife.ai.dto.AiWorkoutRequest;
import com.fitlife.ai.entity.AiWorkoutPlan;
import com.fitlife.core.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("ai")
@RequiredArgsConstructor
@Tag(name = "AI Workout", description = "Tạo và quản lý lịch tập thông minh bằng AI")
public class AiController {

    private final AiService aiService;

    @PostMapping("/workout-plan")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ROLE_MEMBER')")
    @Operation(summary = "Tạo lịch tập bằng AI", description = "Sinh kế hoạch tập luyện cá nhân hóa dựa trên mục tiêu, trình độ và số buổi tập mỗi tuần.")
    public ResponseEntity<ApiResponse<JsonNode>> generatePlan(
            @Valid @RequestBody AiWorkoutRequest request,
            Principal principal) {

        JsonNode aiPlan = aiService.generateWorkoutPlan(principal.getName(), request);

        return ResponseEntity.ok(ApiResponse.success(aiPlan, "Phác đồ cá nhân hóa đã được AI tạo và lưu vào lịch sử thành công!"));
    }

    @GetMapping("/history")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ROLE_MEMBER')")
    @Operation(summary = "Lịch sử AI workout", description = "Lấy danh sách các kế hoạch tập luyện do AI đã tạo cho hội viên.")
    public ResponseEntity<ApiResponse<List<AiWorkoutPlan>>> getHistory(Principal principal) {
 
        List<AiWorkoutPlan> history = aiService.getMemberHistory(principal.getName());

        return ResponseEntity.ok(ApiResponse.success(history, "Lấy danh sách lịch sử tư vấn AI thành công."));
    }

    @PostMapping("/activate/{planId}")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ROLE_MEMBER')")
    @Operation(summary = "Kích hoạt kế hoạch AI", description = "Đưa một kế hoạch AI đã tạo trở thành lịch tập chính thức của hội viên.")
    public ResponseEntity<ApiResponse<String>> activatePlan(@PathVariable Long planId) {

        aiService.activatePlan(planId);

        return ResponseEntity.ok(ApiResponse.success(
                "Activated Plan ID: " + planId,
                "Kích hoạt lịch tập thành công! Lịch tập AI hiện đã trở thành lịch tập chính thức của bạn."
        ));
    }
}