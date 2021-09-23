package com.dev.gslentrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.repositories.EntregaRepository;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

	@Autowired
	private EntregaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Entrega>> findAll() {
		List<Entrega> list = repository.findAll();
		return ResponseEntity.ok(list);
	}	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Entrega> findById(@PathVariable Long id) {
		Entrega obj = repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}
}
