package com.example.ldbc41.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

        // Configurar las opciones de CORS según tus necesidades
        config.setAllowCredentials(true);
        config.addAllowedOrigin(urlFront);
        //config.addAllowedOrigin("+");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedOriginPattern(urlFront);
        config.addAllowedOriginPattern("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
