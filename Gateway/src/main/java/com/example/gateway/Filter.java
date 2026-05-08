package com.example.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class Filter implements WebFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain){
        String path = exchange.getRequest().getURI().getPath();
        if (path.equals("/api/v1/users/login") || path.equals("/api/v1/users/register")) {
            return chain.filter(exchange);
        }

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if(authHeader==null || !authHeader.startsWith("Bearer")){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String jwt = authHeader.substring(7);
            if(!jwtService.validateToken(jwt)){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            Long userId = jwtService.extractUserId(jwt);
            UserRole role = jwtService.extractRole(jwt);

            if(userId == null){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, null, List.of(authority));
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("userId", String.valueOf(userId))
                    .header("role", String.valueOf(role))
                    .build();
            ServerWebExchange exchangee = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(exchangee)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(token));

    }
}
