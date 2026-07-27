package com.snehashis.reviewforge.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI reviewForgeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ReviewForge API")
                        .description("REST API documentation for ReviewForge.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("ReviewForge"))
                        .license(new License()
                                .name("MIT License")))
                .externalDocs(new ExternalDocumentation()
                .       description("Project Documentation")
                        .url("https://github.com/SnehashisDasgupta/reviewforge"));
    }
}
