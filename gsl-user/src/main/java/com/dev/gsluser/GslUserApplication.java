package com.dev.gsluser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GslUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(GslUserApplication.class, args);
	}

}
