package com.fitlife.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // Bắt buộc phải có @Data để Lombok tự sinh ra hàm getPaymentMethod()
public class SubscriptionCreationRequest {

    @NotNull(message = "ID Hội viên không được để trống")
    private Long memberId;

    @NotNull(message = "ID Gói tập không được để trống")
    private Long packageId;

    // THÊM TRƯỜNG NÀY ĐỂ HẾT BÁO ĐỎ
    private String paymentMethod;
}