package com.fitlife.subscription.controller;

import com.fitlife.core.response.ApiResponse;
import com.fitlife.subscription.dto.SubscriptionCreationRequest;
import com.fitlife.subscription.dto.SubscriptionResponse;
import com.fitlife.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<ApiResponse<SubscriptionResponse>> createSubscription(
            @RequestBody SubscriptionCreationRequest request,
            Principal principal) {
        SubscriptionResponse result = subscriptionService.createSubscription(principal.getName(), request);
        return ResponseEntity.ok(ApiResponse.success(result, "Tạo đơn hàng PENDING thành công"));
    }
}