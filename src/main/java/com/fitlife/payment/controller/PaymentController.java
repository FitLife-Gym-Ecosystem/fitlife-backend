package com.fitlife.payment.controller;

import com.fitlife.core.response.ApiResponse;
import com.fitlife.payment.dto.PaymentResponse;
import com.fitlife.payment.mapper.PaymentMapper;
import com.fitlife.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "Payment Management", description = "Tạo link thanh toán và xử lý callback VNPay")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping("/create-payment")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ROLE_MEMBER')")
    @Operation(summary = "Tạo link thanh toán VNPay", description = "Sinh URL thanh toán cho subscription đã tạo của hội viên.")
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(
            @Parameter(description = "ID của subscription cần thanh toán", example = "1001")
            @RequestParam("subscriptionId") Long subscriptionId,
            HttpServletRequest request
    ) {
        String paymentUrl = paymentService.createPaymentUrl(subscriptionId, request);
        PaymentResponse paymentResponse = paymentMapper.toResponse(paymentUrl, subscriptionId);

        return ResponseEntity.ok(ApiResponse.success(paymentResponse, "Tạo link thanh toán thành công"));
    }

    @GetMapping("/vnpay-return")
    @PreAuthorize("permitAll()")
    @SecurityRequirements()
    @Operation(summary = "VNPay return callback", description = "Endpoint callback public được VNPay redirect về sau khi thanh toán.")
    public ResponseEntity<ApiResponse<String>> paymentReturn(HttpServletRequest request) {
        String result = paymentService.processPaymentReturn(request);

        if ("SUCCESS".equals(result)) {
            return ResponseEntity.ok(ApiResponse.success(result, "Thanh toán thành công. Gói tập đã được kích hoạt!"));
        }

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(400, "Giao dịch thất bại hoặc đã bị hủy bỏ.", result));
    }
}