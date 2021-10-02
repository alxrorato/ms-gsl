package com.dev.gslentrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient(name = "gsl-cliente")
@EnableFeignClients
@SpringBootApplication
public class GslEntregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GslEntregaApplication.class, args);
	}

}
