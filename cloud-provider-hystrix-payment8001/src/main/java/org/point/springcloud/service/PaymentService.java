package org.point.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问,肯定OK
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + "  方法名: paymentInfo_OK(), id = " + id + " O(∩_∩)O哈哈~";
    }

    /**
     * 超时访问
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = 3;
        // int i = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + "  方法名: paymentInfo_Timeout(), id = " + id + "  耗时: "+timeNumber+"秒钟(s)";
    }

    /**
     * 超时访问(降级处理方法)
     */
    public String paymentInfo_TimeoutHandle(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + "  系统繁忙,请稍后再试! id = " + id + " o(╥﹏╥)o";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号: " + simpleUUID;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数,请稍后再试! ~~~ id=" + id;
    }
}
