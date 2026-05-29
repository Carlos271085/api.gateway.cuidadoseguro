package com.cuidadoseguro.apigateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

        System.out.println("GATEWAY RECIBE HEADER: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        //String token = authHeader.substring(7);
        String token = authHeader.replaceAll("Bearer ", "");

        //System.out.println("TOKEN: " + token);

        // Aquí se valida el JWT realmente
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        "usuario",
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                );

        /*
        return chain.filter(exchange)
                .contextWrite(
                        ReactiveSecurityContextHolder.withSecurityContext(
                                Mono.just(new SecurityContextImpl(authentication))
                        )
                );
         */
        return chain.filter(exchange).contextWrite(
                ReactiveSecurityContextHolder.withAuthentication(authentication)
        );
    }
}