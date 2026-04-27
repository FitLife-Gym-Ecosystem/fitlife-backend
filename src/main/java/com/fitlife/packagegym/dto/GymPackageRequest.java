package com.fitlife.packagegym.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "GymPackageRequest", description = "Payload tạo hoặc cập nhật gói tập")
public class GymPackageRequest {

    @Schema(description = "Tên gói tập", example = "Premium 12 Months")
    @NotBlank(message = "Tên gói tập không được để trống")
    private String name;

    @Schema(description = "Giá tiền", example = "1200000")
    @NotNull(message = "Giá tiền không được để trống")
    @Min(value = 0, message = "Giá tiền không được nhỏ hơn 0")
    private BigDecimal price;

    @Schema(description = "Thời hạn gói tập theo tháng", example = "12")
    @NotNull(message = "Thời hạn không được để trống")
    @Min(value = 1, message = "Thời hạn phải ít nhất 1 tháng")
    private Integer durationMonths;

    @Schema(description = "Mô tả gói tập", example = "Không giới hạn số lần sử dụng trong 12 tháng")
    private String description;
}