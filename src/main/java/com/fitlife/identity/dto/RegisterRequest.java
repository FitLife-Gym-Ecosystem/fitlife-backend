package com.fitlife.identity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "RegisterRequest", description = "Payload đăng ký tài khoản mới")
public class RegisterRequest {
    @Schema(description = "Tên đăng nhập", example = "member01")
    @NotBlank(message = "Username not be empty")
    private String username;

    @Schema(description = "Mật khẩu", example = "P@ssw0rd123")
    @NotBlank(message = "Password not be empty")
    private String password;

    @Schema(description = "Họ và tên", example = "Nguyen Van A")
    @NotBlank(message = "Full name not be empty")
    private String fullName;

    @Schema(description = "Số điện thoại", example = "0912345678")
    @NotBlank(message = "Phone not be empty")
    private String phone;

    @Schema(description = "Địa chỉ email", example = "member01@fitlife.local")
    @Email(message = "Email should be valid")
    private String email;
}