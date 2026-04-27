package com.fitlife.identity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "UserCreationRequest", description = "Payload tạo user hệ thống")
public class UserCreationRequest {
    @Schema(description = "Tên đăng nhập", example = "staff01")
    private String username;
    @Schema(description = "Mật khẩu", example = "Admin@123")
    private String password;
    @Schema(description = "Vai trò tài khoản", example = "ROLE_STAFF")
    private String role;
}
