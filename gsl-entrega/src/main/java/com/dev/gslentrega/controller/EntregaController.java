package com.dev.gslentrega.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.request.EntregaRequest;
import com.dev.gslentrega.response.Cliente;
import com.dev.gslentrega.service.EntregaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/entregas")
@Slf4j
public class EntregaController {

	@Autowired
	private EntregaService entregaService;
	
	@PostMapping("solicitar")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> solicitarEntrega(@Valid @RequestBody EntregaRequest entregaRequest) {
		return new ResponseEntity<>(entregaService.cadastrarEntrega(entregaRequest), HttpStatus.CREATED);
	}
	
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

	@GetMapping(value = "/getCliente/{cnpj}")
	public ResponseEntity<Cliente> getCliente(@PathVariable Long cnpj) {
		log.info("Dentro do endpoint getCliente no gsl-entrega buscando pelo cnpj [{}]", cnpj);
		Cliente cliente = entregaService.getCliente(cnpj);
		return ResponseEntity.ok(cliente);
	}

}
