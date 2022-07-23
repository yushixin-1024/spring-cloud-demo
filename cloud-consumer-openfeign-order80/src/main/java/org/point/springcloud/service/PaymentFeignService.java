package org.point.springcloud.service;

import org.point.springcloud.entities.CommonResult;
import org.point.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> get(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    CommonResult<Payment> timeout();
}
