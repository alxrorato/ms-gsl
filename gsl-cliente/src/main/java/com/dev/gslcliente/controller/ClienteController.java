package com.dev.gslcliente.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
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

import lombok.extern.slf4j.Slf4j;

@RefreshScope
@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClienteController {

	@Autowired
	private Environment env;
	
	@Autowired
	private ClienteService clienteService;
	
	@Value("${test.config}") //nome do arquivo de configuração criado no Github
	private String testConfig;
	
	@GetMapping(value = "/configs")
	public ResponseEntity<Void> getConfigs() {
		log.info("CONFIG = " + testConfig);
		return ResponseEntity.noContent().build();
	}		

	
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
		log.info("PORT = " + env.getProperty("local.server.port"));
		Cliente cliente = clienteService.buscarClienteById(id);
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping(path = "buscarPorCnpj/{cnpj}")
	public ResponseEntity<?> buscarClientesByCnpj(@PathVariable Long cnpj) {
		log.info("PORT = " + env.getProperty("local.server.port"));
		log.info("Buscando cliente pelo cnpj [{}] dentro do gsl-cliente", cnpj);
		//teste p/ estourar o timeout do balancemanto de carga, que no Ribbon o padrão é de 1s
		/*
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
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
