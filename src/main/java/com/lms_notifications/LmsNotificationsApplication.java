package com.lms_notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LmsNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsNotificationsApplication.class, args);
	}

}
