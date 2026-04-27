package com.fitlife.subscription.controller;

import com.fitlife.core.response.ApiResponse;
import com.fitlife.subscription.dto.SubscriptionCreationRequest;
import com.fitlife.subscription.dto.SubscriptionResponse;
import com.fitlife.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscription Management", description = "Tạo và quản lý đăng ký gói tập của hội viên")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    @Operation(summary = "Tạo đăng ký gói tập", description = "Khởi tạo subscription cho hội viên theo gói tập đã chọn và trạng thái chờ thanh toán.")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> createSubscription(
            @RequestBody SubscriptionCreationRequest request,
            Principal principal) {
        SubscriptionResponse result = subscriptionService.createSubscription(principal.getName(), request);
        return ResponseEntity.ok(ApiResponse.success(result, "Tạo đơn hàng PENDING thành công"));
    }
}