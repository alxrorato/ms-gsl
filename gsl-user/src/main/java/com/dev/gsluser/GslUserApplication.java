package com.dev.gsluser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableEurekaClient
@SpringBootApplication
public class GslUserApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(GslUserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("BCRYPT 1 = " + passwordEncoder.encode("123456"));
		//System.out.println("BCRYPT 2 = " + passwordEncoder.encode("123457"));
		//System.out.println("BCRYPT 3 = " + passwordEncoder.encode("123458"));
		//System.out.println("BCRYPT 4 = " + passwordEncoder.encode("123459"));
	}

}
