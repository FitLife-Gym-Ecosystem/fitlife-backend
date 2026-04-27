package com.fitlife.identity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "LoginRequest", description = "Payload đăng nhập bằng username và mật khẩu")
public class LoginRequest {

    @Schema(description = "Tên đăng nhập", example = "member01")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(description = "Mật khẩu", example = "P@ssw0rd123")
    @NotBlank(message = "Password cannot be blank")
    private String password;
}