package com.fitlife.analytics.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeakHourTrafficResponse {
    private String timeFrame; // VD: "17:00 - 18:00"
    private int checkInCount;
    private String trafficStatus; // Xanh, Vàng, Đỏ
}