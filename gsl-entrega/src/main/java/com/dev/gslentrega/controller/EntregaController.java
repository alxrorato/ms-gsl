package com.dev.gslentrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.service.EntregaService;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

	@Autowired
	private EntregaService entregaService;
	
	@GetMapping
	public ResponseEntity<List<Entrega>> findAll() {
		List<Entrega> list = entregaService.buscarEntregas();
		return ResponseEntity.ok(list);
	}	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Entrega> findById(@PathVariable Long id) {
		Entrega entrega = entregaService.buscarEntregaById(id);
		return ResponseEntity.ok(entrega);
	}
}
