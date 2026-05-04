package com.ai.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "F&B AI Management API",
                version = "1.0",
                description = "API documentation for AI Demo application",
                contact = @Contact(
                        name = "AI Demo Support",
                        email = "knguyen19092004@gmail.com"
                )
        ),
        //Cau hinh security
        security = {
                @SecurityRequirement(name = "bearAuth")
        }
)

@SecurityScheme(
        name = "bearAuth",
        description = "ENTER THE TOKEN",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER


)
public class OpenApiConfig {
}
