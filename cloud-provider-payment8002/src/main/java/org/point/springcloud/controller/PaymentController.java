package org.point.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.point.springcloud.entities.CommonResult;
import org.point.springcloud.entities.Payment;
import org.point.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果:{}", result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功,端口号:"+port, result);
        } else {
            return new CommonResult<>(500, "插入数据库失败,端口号:"+port, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:{}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功,端口号:"+port, payment);
        } else {
            return new CommonResult<>(500, "没有对应记录,查询ID:"+id+",端口号:"+port, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service:[{}]", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("{}\t{}\t{}\t{}", instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/timeout")
    public String timeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(30);
        return port;
    }
}
