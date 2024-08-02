package com.shelter_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ShelterProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelterProjectApplication.class, args);
	}

}
