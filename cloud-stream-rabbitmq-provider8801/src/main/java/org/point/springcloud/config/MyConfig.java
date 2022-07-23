package org.point.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MyConfig {

    @Bean
    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }
}
