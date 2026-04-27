package com.fitlife.subscription.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Schema(name = "SubscriptionResponse", description = "Thông tin subscription trả về từ hệ thống")
public class SubscriptionResponse {
    @Schema(description = "ID subscription", example = "5001")
    private Long id;
    @Schema(description = "ID hội viên", example = "100")
    private Long memberId;
    @Schema(description = "ID gói tập", example = "1")
    private Long packageId;
    @Schema(description = "Tên gói tập", example = "Premium 12 Months")
    private String packageName;
    @Schema(description = "Ngày bắt đầu", example = "2026-04-27")
    private LocalDate startDate;
    @Schema(description = "Ngày kết thúc", example = "2027-04-26")
    private LocalDate endDate;
    @Schema(description = "Trạng thái subscription", example = "PENDING")
    private String status;
}