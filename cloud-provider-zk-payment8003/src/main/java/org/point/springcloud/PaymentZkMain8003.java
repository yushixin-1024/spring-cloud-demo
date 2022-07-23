package org.point.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentZkMain8003 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentZkMain8003.class, args);
    }

}
