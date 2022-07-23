package org.point.springcloud;

import org.point.springcloud.config.ApplicationContextConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@LoadBalancerClients(defaultConfiguration = ApplicationContextConfig.class)
public class OrderLoadBalancerMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderLoadBalancerMain80.class, args);
    }

}
