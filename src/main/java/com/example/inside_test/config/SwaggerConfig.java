package com.example.inside_test.config;

import com.example.inside_test.service.JWTFilter;
import com.example.inside_test.service.IJWTService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class SwaggerConfig {
    @Value("${api.version}")
    private String version_api;

    private final IJWTService IJWTService;

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter(IJWTService);
    }

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Документация REST API сервиса сообщений")
                        .version(version_api)
                );
    }
}
