package com.fitlife.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "MemberCreationRequest", description = "Payload tạo hoặc cập nhật hồ sơ hội viên")
public class MemberCreationRequest {
    @Schema(description = "ID tài khoản người dùng liên kết", example = "10")
    private Long userId;
    @Schema(description = "Họ và tên hội viên", example = "Nguyen Van A")
    private String fullName;
    @Schema(description = "Số điện thoại", example = "0912345678")
    private String phone;
    @Schema(description = "Địa chỉ email", example = "member01@fitlife.local")
    private String email;
}