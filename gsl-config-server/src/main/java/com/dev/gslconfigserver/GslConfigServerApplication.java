package com.dev.gslconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class GslConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GslConfigServerApplication.class, args);
	}

}
