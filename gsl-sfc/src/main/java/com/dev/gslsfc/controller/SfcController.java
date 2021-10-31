package com.dev.gslsfc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gslsfc.request.CteRequest;
import com.dev.gslsfc.response.CteResponse;
import com.dev.gslsfc.service.SfcService;

@RestController
@RequestMapping("/sfc")
public class SfcController {

	@Autowired
	private SfcService sfcService;
	
	@PostMapping("/cadastrarCte")
	public ResponseEntity<?> adicionarCliente(@Valid @RequestBody CteRequest cteRequest) {
		CteResponse cteResponse = sfcService.cadastrarCte(cteRequest);
		return new ResponseEntity<>(cteResponse, cteResponse.isCteRecebido() ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}
