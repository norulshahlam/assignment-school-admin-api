package com.shah.assignmentschooladminapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author NORUL
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class AssignmentSchoolAdminApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentSchoolAdminApiApplication.class, args);
	}

}
