package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------PaymentFallbackService fall back-paymetnInfo_OK,/(ćoć)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-------PaymentFallbackService fall back-paymetnInfo_timeout,/(ćoć)/~~";
    }
}
