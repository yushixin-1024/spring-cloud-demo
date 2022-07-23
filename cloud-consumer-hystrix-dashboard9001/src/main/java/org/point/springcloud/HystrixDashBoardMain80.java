package org.point.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashBoardMain80 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashBoardMain80.class, args);
    }

}
