package com.shah.assignmentschooladminapi.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author NORUL
 */
@Configuration
@SecurityScheme(
        name = "basicAuth", // can be set to anything
        type = SecuritySchemeType.HTTP,
        scheme = "basic",
        description = "Basic authentication"
)
public class OpenApiConfig {

    @Bean
    public io.swagger.v3.oas.models.security.SecurityScheme getSecurityScheme() {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .scheme("basic")
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                .scheme("basic")
                .description("Basic auth");
    }

    @Bean
    public OpenAPI customOpenApi(OpenApiProperties properties) {
        return new OpenAPI()
                .info(getInfo(properties)
                        .contact(getContact())
                        .termsOfService("getTermsOfService")
                        .license(getLicense())
                ).servers(List.of(getServer1(), getServer2()));
    }



    private Server getServer1() {
        return new Server()
                .description("localhost")
                .url("http://localhost:8081");
    }

    private Server getServer2() {
        return new Server()
                .description("uat")
                .url("http://localhost:8082");
    }

    private Info getInfo(OpenApiProperties properties) {
        return new Info()
                .title(properties.getProjectTitle())
                .description(properties.getProjectDescription())
                .version(properties.getProjectVersion())
                .license(getLicense());
    }

    private Contact getContact() {
        return new Contact()
                .url("https://www.youtube.com/watch?v=2o_3hjUPAfQ&ab_channel=BoualiAli")
                .name("Enquiry")
                .email("norulshahlam@gmail.com");
    }

    private License getLicense() {
        return new License()
                .name("Unlicensed")
                .url("https://unlicense.org/");
    }
}
