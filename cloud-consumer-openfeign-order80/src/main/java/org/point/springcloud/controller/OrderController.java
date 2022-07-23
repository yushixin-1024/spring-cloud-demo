package org.point.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.point.springcloud.entities.CommonResult;
import org.point.springcloud.entities.Payment;
import org.point.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        return paymentFeignService.get(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public CommonResult<Payment> timeout() {
        return paymentFeignService.timeout();
    }
}
