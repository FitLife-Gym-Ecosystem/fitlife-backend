package com.fitlife.analytics.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevenueReportResponse {
    private double totalVnPay;
    private double totalPos;
    private double totalRevenue;
}