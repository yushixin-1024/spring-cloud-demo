package org.point.springcloud.controller;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/zk")
    public String paymentZk() {
        return "SpringCloud with Zookeeper: " + port + "\t" + UUID.fastUUID();
    }
}
