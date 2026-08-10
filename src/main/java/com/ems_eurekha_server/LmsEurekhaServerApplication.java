package com.ems_eurekha_server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class LmsEurekhaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsEurekhaServerApplication.class, args);
	}

}
