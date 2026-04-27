package com.fitlife.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "HealthMetricRequest", description = "Payload cập nhật chỉ số sức khỏe hội viên")
public class HealthMetricRequest {

    @Schema(description = "Cân nặng (kg)", example = "72.5")
    @NotNull(message = "Cân nặng không được để trống")
    @Min(value = 10, message = "Cân nặng phải lớn hơn 10kg")
    private Double weight; // Unit : kg

    @Schema(description = "Chiều cao (cm)", example = "175.0")
    @NotNull(message = "Chiều cao không được để trống")
    @Min(value = 50, message = "Chiều cao phải lớn hơn 50cm")
    private Double height; // Unit: cm
}