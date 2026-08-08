package com.lms_api_gatway_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LmsApiGatwayServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApiGatwayServicesApplication.class, args);
	}

}
