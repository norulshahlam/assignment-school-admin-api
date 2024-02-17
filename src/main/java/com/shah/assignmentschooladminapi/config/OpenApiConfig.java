package com.shah.assignmentschooladminapi.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
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
                .description("Basic auth");
    }

    @Bean
    public OpenAPI customOpenApi(SpringDocProperties properties) {
        return new OpenAPI()
                .info(getInfo(properties)
                        .contact(getContact(properties))
                        .license(getLicense(properties))
                ).servers(List.of(getServer1(), getServer2())).security(ff());
    }

    private List<SecurityRequirement> ff() {
        return List.of(new SecurityRequirement().addList("faaf","afaf"));
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

    private Info getInfo(SpringDocProperties properties) {
        return new Info()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .license(getLicense(properties));
    }

    private Contact getContact(SpringDocProperties properties) {
        return new Contact()
                .url(properties.getUrl())
                .name(properties.getName())
                .email(properties.getEmail());
    }

    private License getLicense(SpringDocProperties properties) {
        return new License()
                .name(properties.getLicenseName())
                .url(properties.getLicenseUrl());
    }
}
