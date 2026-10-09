package com.cd.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("catalog-route",r -> r
                        .path("/api/catalog/**")
                        .filters(f -> f
                                .stripPrefix(2)
                                .prefixPath("/api")
                        )
                        .uri("lb://catalog-service"))
                .route("lending-route",r -> r
                        .path("/api/lending/**")
                        .filters(f -> f
                                .stripPrefix(2)
                                .prefixPath("/api"))
                        .uri("lb://lending-service"))
                .route("identity-route",r -> r
                        .path("/api/identity/**")
                        .filters(f -> f
                                .stripPrefix(2)
                                .prefixPath("/api"))
                        .uri("lb://identity-service"))
                .build();
    }
}
