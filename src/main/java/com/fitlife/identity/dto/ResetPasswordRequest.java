package com.fitlife.identity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "ResetPasswordRequest", description = "Payload đặt lại mật khẩu bằng OTP")
public class ResetPasswordRequest {
    @Schema(description = "Địa chỉ email", example = "member01@fitlife.local")
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @Schema(description = "Mã OTP xác thực", example = "123456")
    @NotBlank(message = "Mã OTP không được để trống")
    private String otp;

    @Schema(description = "Mật khẩu mới", example = "NewP@ss123")
    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Size(min = 6, message = "Mật khẩu phải từ 6 ký tự trở lên")
    private String newPassword;
}