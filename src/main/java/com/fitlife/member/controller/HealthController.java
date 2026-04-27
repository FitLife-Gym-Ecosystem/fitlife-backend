package com.fitlife.member.controller;

import com.fitlife.core.response.ApiResponse;
import com.fitlife.member.dto.HealthMetricRequest;
import com.fitlife.member.entity.HealthMetric;
import com.fitlife.member.service.HealthMetricService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/health-metrics")
@RequiredArgsConstructor
@Tag(name = "Health Management", description = "APIs dành cho quản lý chỉ số sức khỏe hội viên")
public class HealthController {

    private final HealthMetricService healthMetricService;

    @PostMapping
    public ResponseEntity<ApiResponse<HealthMetric>> addMetric(
            @Valid @RequestBody HealthMetricRequest request,
            Principal principal) {
        HealthMetric savedMetric = healthMetricService.addHealthMetric(principal.getName(), request);
        return ResponseEntity.ok(ApiResponse.success(savedMetric, "Cập nhật chỉ số sức khỏe thành công!"));
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<HealthMetric>>> getHistory(Principal principal) {
        List<HealthMetric> history = healthMetricService.getMemberHistory(principal.getName());
        return ResponseEntity.ok(ApiResponse.success(history, "Lấy lịch sử sức khỏe thành công!"));
    }
}