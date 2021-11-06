package com.dev.gslapigatewayzuul.config;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Configuration
@Primary // Esta classe tem preferência quando há vários beans
public class SwaggerConfig implements SwaggerResourcesProvider {

	/// Fonte: https://programmer.help/blogs/routing-gateway-zuul-aggregating-api-documents-with-swagger-2.html

	@Value(value = "${spring.application.name}")
	private String springApplicationName;
	
	@Value(value = "${base.package}")
	private String basePackage;
	
	@Value(value = "${swagger.enabled}")
	boolean swaggerEnabled;

	@Autowired
	RouteLocator routeLocator;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.enable(swaggerEnabled).select()
				.apis(basePackage(basePackage))
				.paths(PathSelectors.any()).build().pathMapping("/");
	}

	//Setting up api information
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Routing Gateway(Zuul): utilize swagger2 polymerization API File")
				.description("oKong | Staggering ape")
				.contact(new Contact("Alexandre Rorato Carneiro", null, null))
				.version("1.0")
				.build();
	}
	
	
	@Override
	public List<SwaggerResource> get() {
		//Dynamic introduction of micro services using routeLocator
		List<SwaggerResource> resources = new ArrayList<>();
		resources.add(swaggerResource(springApplicationName,"/v2/api-docs","2.0"));
		//Recycling Lambda expressions to simplify code
		routeLocator.getRoutes().forEach(route ->{
			//Dynamic acquisition
			resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "2.0"));
		});
		//You can also directly inherit the Consumer interface
//		routeLocator.getRoutes().forEach(new Consumer<Route>() {
//
//			@Override
//			public void accept(Route t) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		return resources;
	}

	private SwaggerResource swaggerResource(String name,String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		//swaggerResource.setUrl("http://localhost:61568/v2/api-docs");
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}	
}
