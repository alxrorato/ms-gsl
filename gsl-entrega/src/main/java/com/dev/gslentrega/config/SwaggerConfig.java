package com.dev.gslentrega.config;

import static java.util.Collections.singleton;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Value(value = "${base.package}")
	private String basePackage;
	
	@Value(value = "${swagger.enabled}")
	private boolean swaggerEnabled;

	@Bean
	public Docket api() {
        
        return new Docket(DocumentationType.SWAGGER_2)
        		.enable(swaggerEnabled).select()
                .apis(basePackage(basePackage))
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

}
