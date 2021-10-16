package com.dev.gslparceira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gslparceira.service.ParceiraService;

@RestController
@RequestMapping("/parceira")
public class ParceiraController {
	
	@Autowired
	private ParceiraService parceiraService; 

	@GetMapping(path = "solicitarParceria/{cnpjSolicitante}")
	public ResponseEntity<?> solicitarParceria(@PathVariable Long cnpjSolicitante) {
		return new ResponseEntity<>(parceiraService.solicitarParceria(cnpjSolicitante), HttpStatus.OK);
	}

}
