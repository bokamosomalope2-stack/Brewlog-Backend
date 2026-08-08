package com.brewlog.brewlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addMapping(String path) {
                corsRegistry.addMapping("/api/**") // Protects your brew routes
                        .allowedOrigins(
                                "http://localhost:5173", // Permits local Vite testing
                                "https://*.vercel.app"   // Permits all Vercel deployments safely via wildcard
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
