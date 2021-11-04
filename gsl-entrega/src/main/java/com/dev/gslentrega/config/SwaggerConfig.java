package com.dev.gslentrega.config;

import static java.util.Collections.singleton;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Value(value = "${gslentrega.base.package}")
	private String basePackage;
	
	@Bean
	public Docket api() {
        
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage((basePackage)))
                .build()
        		.apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .consumes(singleton(MediaType.APPLICATION_JSON_VALUE))
                .produces(singleton(MediaType.APPLICATION_JSON_VALUE));
	}

    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("GSL")
    			.description("Gestão de Serviços de Logística - Módulo Entrega")
    			.version("1.0")
    			.contact(new Contact("Alexandre Rorato Carneiro", null, null))
    			.build();
    					
    }

    private Object apiKey() {
        // TODO Auto-generated method stub
        return null;
    }	
}
