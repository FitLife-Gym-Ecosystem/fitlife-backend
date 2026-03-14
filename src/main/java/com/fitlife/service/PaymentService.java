package com.fitlife.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface PaymentService {
    String createPaymentUrl(Long subscriptionId, HttpServletRequest request);
    String processPaymentReturn(HttpServletRequest request);
}