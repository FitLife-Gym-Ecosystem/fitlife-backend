package com.fitlife.analytics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardResponse {
    private long totalMembers;
    private long activeMembers;
    private double totalRevenue;
    private long totalCheckinsToday;
    private Map<String, Double> revenueByMonth;
    // Tương lai sẽ nhúng thêm RevenueReportResponse và PeakHourTrafficResponse vào đây
}