package com.dev.gslgoogleservicesmock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gslgoogleservicesmock.controller.service.GoogleServicesMockService;
import com.dev.gslgoogleservicesmock.response.LocalizacaoGoogleResponse;

@RestController
@RequestMapping("/servicos")
public class GoogleServicesMockController {

	@Autowired
	private GoogleServicesMockService googleServicesMockService; 

	@GetMapping(path = "/obterLocalizacao")
	public ResponseEntity<LocalizacaoGoogleResponse> obterLocalizacao() {
		return new ResponseEntity<>(googleServicesMockService.obterLocalizacao(), HttpStatus.OK);
	}
	
}
