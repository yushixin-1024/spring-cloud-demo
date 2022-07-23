package org.point.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 配置优先级高于application.yml,RouteLocator配置了,application.yml将会失效
     * @param builder
     * @return
     */
    /*@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_routh_point", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"));
        routes.route("path_routh_point2", r -> r.path("/**").uri("https://www.baidu.com/"));
        return routes.build();
    }*/
}
