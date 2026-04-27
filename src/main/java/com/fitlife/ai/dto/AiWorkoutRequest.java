package com.fitlife.ai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "AiWorkoutRequest", description = "Payload yêu cầu AI tạo lịch tập cá nhân hóa")
public class AiWorkoutRequest {


    @Schema(description = "Mục tiêu tập luyện", example = "Tăng cơ giảm mỡ")
    @NotBlank(message = "Mục tiêu không được để trống (VD: Tăng cơ giảm mỡ)")
    private String goal;

    @Schema(description = "Trình độ hiện tại", example = "Beginner")
    @NotBlank(message = "Trình độ không được để trống (VD: Beginner, Intermediate)")
    private String fitnessLevel;

    @Schema(description = "Số buổi tập mỗi tuần", example = "5")
    @Min(1)
    @Max(7)
    private int daysPerWeek;

    @Schema(description = "Chấn thương hoặc hạn chế cần lưu ý", example = "Đau lưng dưới")
    private String injuries;
    @Schema(description = "Thiết bị sẵn có để tập", example = "Dumbbell, Barbell")
    private String equipment;
    @Schema(description = "Sở thích dinh dưỡng", example = "High protein")
    private String dietPreference;
}