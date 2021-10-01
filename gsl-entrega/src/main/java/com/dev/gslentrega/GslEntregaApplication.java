package com.dev.gslentrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GslEntregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GslEntregaApplication.class, args);
	}

}
