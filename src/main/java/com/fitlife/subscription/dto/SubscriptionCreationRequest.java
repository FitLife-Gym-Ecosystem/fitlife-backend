package com.fitlife.subscription.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "SubscriptionCreationRequest", description = "Payload tạo subscription cho hội viên")
public class SubscriptionCreationRequest {

    @Schema(description = "ID hội viên", example = "100")
    @NotNull(message = "ID Hội viên không được để trống")
    private Long memberId;

    @Schema(description = "ID gói tập", example = "1")
    @NotNull(message = "ID Gói tập không được để trống")
    private Long packageId;

    @Schema(description = "Phương thức thanh toán", example = "VNPAY")
    private String paymentMethod;
}