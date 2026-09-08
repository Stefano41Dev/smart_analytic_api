package com.stefano.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Stefano",
                version = "1.0",
                description = "Documentación API con JWT"
        )
)
public class Swagger {
}
