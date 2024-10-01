package com.example.ldbc41.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Value("${var.url-front}")
    private String urlFront;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Configurar las opciones de CORS
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Permitir solo el origen especificado
        config.addAllowedHeader("*");
        config.addAllowedMethod("*"); // Permitir todos los métodos HTTP

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
