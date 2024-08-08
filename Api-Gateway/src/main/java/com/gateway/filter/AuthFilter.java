package com.example.gateway.filter;

import com.example.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;


    public AuthFilter() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                // Check if authorization header is not present
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return Mono.error(new RuntimeException("Missing authorization header"));
                }

                String header = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (header != null && header.startsWith("Bearer ")) {
                    header = header.substring(7);
                }

                try {
                    // Validate token
                    jwtUtil.validateToken(header);
                } catch (Exception e) {
                    return Mono.error(new RuntimeException("Unauthorized access"));
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{

    }
}
