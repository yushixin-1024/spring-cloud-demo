package org.point.springcloud.service.impl;

import org.point.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService--fallback--paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService--fallback--paymentInfo_Timeout";
    }
}
