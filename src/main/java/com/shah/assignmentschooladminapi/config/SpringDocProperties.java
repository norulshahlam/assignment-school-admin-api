package com.shah.assignmentschooladminapi.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author NORUL
 */
@ConfigurationProperties(prefix = "spring-doc")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class SpringDocProperties {

    private final String title;
    private final String description;
    private final String version;
    private final String url;
    private final String name;
    private final String email;
    private final String licenseName;
    private final String licenseUrl;
}
