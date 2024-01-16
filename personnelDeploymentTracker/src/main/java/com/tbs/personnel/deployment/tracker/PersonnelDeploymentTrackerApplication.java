package com.tbs.personnel.deployment.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonnelDeploymentTrackerApplication {
//	@PostConstruct
//	public void startupApplication() {
//		System.out.println("Swagger URL: http://localhost:8080/swagger-ui/index.html");
//		System.out.println("Database URL: http://localhost:8080/h2-console");
//	}
	public static void main(String[] args) {
		SpringApplication.run(PersonnelDeploymentTrackerApplication.class, args);
	}

}
