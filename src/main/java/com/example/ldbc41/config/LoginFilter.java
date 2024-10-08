package com.example.ldbc41.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class LoginFilter {
    @Autowired
    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(http -> {
                    http.requestMatchers("/auth/login").permitAll();
                    http.requestMatchers("/keycloak/user/insertUser").permitAll();
                    http.requestMatchers("/equipos/todos").permitAll();
                    http.requestMatchers("/paises").permitAll();
                    http.requestMatchers("/equipos/{equipoId}/jugadores").permitAll();
                    http.requestMatchers("/equipos/{equipoId}/jugadorCalificacion").permitAll();
                    http.requestMatchers("/upload/image").permitAll();
                   // http.requestMatchers("/downloadFile").permitAll();
                    http.requestMatchers("/api/jugadores/{cedula}").permitAll();
                    http.anyRequest().authenticated();
                }).oauth2ResourceServer(oauth -> {
                    oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter));
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
