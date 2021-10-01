package com.dev.gslcliente.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gslcliente.entities.Cliente;
import com.dev.gslcliente.request.ClienteRequest;
import com.dev.gslcliente.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> adicionarCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
		return new ResponseEntity<>(clienteService.cadastrarCliente(clienteRequest), HttpStatus.CREATED);
	}

	@PutMapping("atualizar")
	public ResponseEntity<?> atualizarCliente(@Valid @RequestBody Cliente cliente) {
		clienteService.atualizarCliente(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("listar")
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> list = clienteService.buscarClientes();
		return ResponseEntity.ok(list);
	}	

	@GetMapping(value = "buscarPorId/{id}")
	public ResponseEntity<Cliente> buscarClienteById(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarClienteById(id);
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping(path = "buscarPorCnpj/{cnpj}")
	public ResponseEntity<?> buscarClientesByCnpj(@PathVariable Long cnpj) {
		return new ResponseEntity<>(clienteService.buscarClienteByCnpj(cnpj, true), HttpStatus.OK);
	}

	@GetMapping(path = "buscarPorNome/{nome}")
	public ResponseEntity<?> buscarClientesByNome(@PathVariable String nome) {
		return new ResponseEntity<>(clienteService.buscarClientesByNome(nome), HttpStatus.OK);
	}
	
	@GetMapping(path = "buscarPorContemNome/{nome}")
	public ResponseEntity<?> buscarClientesByContainsNome(@PathVariable String nome) {
		return new ResponseEntity<>(clienteService.buscarClientesByContainsNome(nome), HttpStatus.OK);
	}

	@DeleteMapping("excluir/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> excluirCliente(@PathVariable("id") Long id) {
		clienteService.excluirClienteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
