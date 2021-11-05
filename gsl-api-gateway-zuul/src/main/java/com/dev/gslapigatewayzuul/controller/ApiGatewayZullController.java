package com.dev.gslapigatewayzuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/teste")
@Api(tags="gsl-api-gatewayzuul dentreo do swagger")
public class ApiGatewayZullController {
	@GetMapping("/hello")
	@ApiOperation(value="Exemplo",notes="exemplo teste")
	@ApiImplicitParam(name="name",value="Name",example="GSL")
	public String hello(String name) {
		return "Olá," + name + ", este é o Zuul Api Gateway!";
	}
}
