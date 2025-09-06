package com.smarthome.devicemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // tutti gli endpoint
                        .allowedOrigins("*") // accetta richieste da qualsiasi dominio
                        .allowedMethods("*") // GET, POST, PUT, DELETE, OPTIONS
                        .allowedHeaders("*"); // tutti gli header
            }
        };
    }
}
