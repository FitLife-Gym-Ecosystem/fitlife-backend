package com.fitlife.packagegym.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Schema(name = "GymPackageResponse", description = "Thông tin gói tập trả về cho client")
public class GymPackageResponse {
    @Schema(description = "ID gói tập", example = "1")
    private Long id;
    @Schema(description = "Tên gói tập", example = "Premium 12 Months")
    private String name;
    @Schema(description = "Giá tiền", example = "1200000")
    private BigDecimal price;
    @Schema(description = "Thời hạn gói tập theo tháng", example = "12")
    private Integer durationMonths;
    @Schema(description = "Mô tả gói tập", example = "Không giới hạn số lần sử dụng trong 12 tháng")
    private String description;
    @Schema(description = "Trạng thái gói tập", example = "ACTIVE")
    private String status;
    @Schema(description = "Đường dẫn thumbnail của gói", example = "https://cdn.fitlife.local/packages/premium.jpg")
    private String thumbnailUrl;
}