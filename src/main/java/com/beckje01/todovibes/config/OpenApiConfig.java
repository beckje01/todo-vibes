package com.beckje01.todovibes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI todoVibesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Todo Vibes API")
                        .description("A REST API for managing TODO items, built with Spring Boot. The todos are stored in-memory using a ConcurrentHashMap.")
                        .version("0.0.1"));
    }
}
