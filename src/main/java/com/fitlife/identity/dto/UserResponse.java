package com.fitlife.identity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(name = "UserResponse", description = "Thông tin user hệ thống trả về")
public class UserResponse {
    @Schema(description = "ID user", example = "10")
    private Long id;
    @Schema(description = "Tên đăng nhập", example = "staff01")
    private String username;
    @Schema(description = "Vai trò tài khoản", example = "ROLE_STAFF")
    private String role;
    @Schema(description = "Trạng thái tài khoản", example = "ACTIVE")
    private String status;
}