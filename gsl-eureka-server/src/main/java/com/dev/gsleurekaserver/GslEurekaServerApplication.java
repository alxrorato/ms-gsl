package com.dev.gsleurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GslEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GslEurekaServerApplication.class, args);
	}

}
