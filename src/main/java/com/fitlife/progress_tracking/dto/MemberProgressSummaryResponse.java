package com.fitlife.progress_tracking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "MemberProgressSummaryResponse", description = "Báo cáo tổng hợp tiến độ cá nhân của hội viên")
public class MemberProgressSummaryResponse {
    // 1. Membership
    @Schema(description = "Tên hội viên", example = "Nguyen Van A")
    private String memberName;
    @Schema(description = "Tên gói tập hiện tại", example = "Premium 12 Months")
    private String currentPackageName;
    @Schema(description = "Số ngày còn lại của gói", example = "245")
    private Long daysRemaining;

    // 2. Health
    @Schema(description = "Cân nặng hiện tại", example = "72.5")
    private Double currentWeight;
    @Schema(description = "Chiều cao hiện tại", example = "175.0")
    private Double currentHeight;
    @Schema(description = "Chỉ số BMI", example = "23.67")
    private Double bmi;
    @Schema(description = "Phân loại BMI", example = "Normal")
    private String bmiCategory;

    // 3. Workout Plan
    @Schema(description = "Số lượt check-in trong tháng", example = "12")
    private Integer totalCheckinsThisMonth;
    @Schema(description = "Số bài tập đã hoàn thành", example = "48")
    private Integer completedExercises;
    @Schema(description = "Calories ước tính đã đốt", example = "3200")
    private Integer estimatedCaloriesBurned;
}