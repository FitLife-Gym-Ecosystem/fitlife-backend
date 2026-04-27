package com.fitlife.identity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "LoginResponse", description = "Thông tin trả về sau khi đăng nhập thành công")
public class LoginResponse {
    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
    @Schema(description = "Tên đăng nhập", example = "member01")
    private String username;
    @Schema(description = "Vai trò của tài khoản", example = "ROLE_MEMBER")
    private String role;
}