package com.shah.assignmentschooladminapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author NORUL
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class SchoolAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolAdminApplication.class, args);
	}

}
