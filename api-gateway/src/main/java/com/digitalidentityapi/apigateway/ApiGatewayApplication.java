package com.digitalidentityapi.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator digitalIdentityRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/digital-identity/citizen/**")
						.filters( f -> f.rewritePath("/digital-identity/citizen/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CITIZEN"))
				.route(p -> p
						.path("/digital-identity/document/**")
						.filters( f -> f.rewritePath("/digital-identity/document/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://DOCUMENT"))
				.route(p -> p
						.path("/digital-identity/operators/**")
						.filters( f -> f.rewritePath("/digital-identity/operators/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://OPERATORS"))
				.route(p -> p
						.path("/digital-identity/notifications/**")
						.filters( f -> f.rewritePath("/digital-identity/notifications/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://NOTIFICATIONS")).build();
	}

}
