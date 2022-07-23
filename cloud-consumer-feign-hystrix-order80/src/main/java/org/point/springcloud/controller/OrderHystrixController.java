package org.point.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.point.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_GlobalMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info(result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        // int i = 10 / 0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info(result);
        return result;
    }

    /**
     * 超时访问(降级处理方法)
     */
    public String paymentInfo_TimeoutHandle(Integer id) {
        return "消费端80--线程池: " + Thread.currentThread().getName() + "  系统繁忙,请稍后再试! id = " + id + " o(╥﹏╥)o";
    }

    /**
     * 全局fallback方法
     */
    public String paymentInfo_GlobalMethod() {
        return "Global异常处理信息,请稍后再试!o(╥﹏╥)o";
    }
}
