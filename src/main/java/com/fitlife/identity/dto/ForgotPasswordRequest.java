package com.fitlife.identity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "ForgotPasswordRequest", description = "Payload gửi yêu cầu quên mật khẩu")
public class ForgotPasswordRequest {
    @Schema(description = "Địa chỉ email đã đăng ký", example = "member01@fitlife.local")
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
}