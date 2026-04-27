package com.fitlife.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(name = "PaymentResponse", description = "Thông tin link thanh toán VNPay")
public class PaymentResponse {
    @Schema(description = "Trạng thái tạo thanh toán", example = "SUCCESS")
    private String status;
    @Schema(description = "Thông điệp trả về", example = "Tạo link thanh toán thành công")
    private String message;
    @Schema(description = "URL thanh toán", example = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?...")
    private String paymentUrl;
    @Schema(description = "Mô tả đơn hàng", example = "Thanh toan goi tap 12 thang")
    private String orderInfo;
}