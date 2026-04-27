package com.fitlife.attendance.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Schema(name = "CheckInResponse", description = "Kết quả check-in của hội viên")
public class CheckInResponse {
    @Schema(description = "ID hội viên", example = "100")
    private Long memberId;
    @Schema(description = "Tên hội viên", example = "Nguyen Van A")
    private String memberName;
    @Schema(description = "Thời gian check-in", example = "2026-04-27T08:30:00")
    private LocalDateTime checkInTime;
    @Schema(description = "Trạng thái truy cập", example = "ACCESS_GRANTED")
    private String status; // "ACCESS_GRANTED" hoặc "ACCESS_DENIED"
    @Schema(description = "Thông điệp chi tiết", example = "Check-in thành công")
    private String message; // Reason details
}