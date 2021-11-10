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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RefreshScope
@RestController
@RequestMapping("/clientes")
@Slf4j
@Api(value = "Endpoints do módulo de informações cadastrais - cadastro de clientes")
public class ClienteController {

	@Autowired
	private Environment env;
	
	@Autowired
	private ClienteService clienteService;
	
	@Value("${test.config}") //nome do arquivo de configuração criado no Github
	private String testConfig;
	
	@GetMapping(value = "/configs")
	@ApiOperation(hidden = true, value = "")
	public ResponseEntity<Void> getConfigs() {
		log.info("CONFIG = " + testConfig);
		return ResponseEntity.noContent().build();
	}		

	
	@PostMapping("add")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Cadastrar cliente", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente cadastrado"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 422, message = "Cliente já cadastrado")
		})
	public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
		return new ResponseEntity<>(clienteService.cadastrarCliente(clienteRequest), HttpStatus.CREATED);
	}

	@PutMapping("atualizar")
	@ApiOperation(value = "Atualizar cliente", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Cliente não cadastrado")
		})
	public ResponseEntity<Cliente> atualizarCliente(@Valid @RequestBody Cliente cliente) {
		clienteService.atualizarCliente(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("listar")
	@ApiOperation(value = "Listar todos os clientes", response = Cliente.class)
	@ApiResponse(code = 200, message = "Lista de clientes retornada com sucesso")
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> list = clienteService.buscarClientes();
		return ResponseEntity.ok(list);
	}	

	@GetMapping(value = "buscarPorId/{id}")
	@ApiOperation(value = "Buscar cliente por id", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente retornado com sucesso"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Cliente não cadastrado")
		})
	public ResponseEntity<Cliente> buscarClienteById(
			@ApiParam(name = "id", value = "Id do registro no banco de dados")
			@PathVariable Long id) {
		log.info("PORT = " + env.getProperty("local.server.port"));
		Cliente cliente = clienteService.buscarClienteById(id);
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping(path = "buscarPorCnpj/{cnpj}")
	@ApiOperation(value = "Buscar cliente por cnpj", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente retornado com sucesso"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Cliente não cadastrado")
		})
	public ResponseEntity<Cliente> buscarClientesByCnpj(
			@ApiParam(name = "cnpj", value = "CNPJ do cliente")
			@PathVariable Long cnpj) {
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
	@ApiOperation(value = "Buscar cliente por nome comercial", response = Cliente.class)
	@ApiResponse(code = 200, message = "Clientes retornados com sucesso")
	public ResponseEntity<List<Cliente>> buscarClientesByNome(
			@ApiParam(name = "nome", value = "Nome do cliente")
			@PathVariable String nome) {
		return new ResponseEntity<>(clienteService.buscarClientesByNome(nome), HttpStatus.OK);
	}
	
	@GetMapping(path = "buscarPorContemNome/{nome}")
	@ApiOperation(value = "Buscar cliente por parte do nome comercial", response = Cliente.class)
	@ApiResponse(code = 200, message = "Clientes retornados com sucesso")
	public ResponseEntity<List<Cliente>> buscarClientesByContainsNome(
			@ApiParam(name = "nome", value = "Nome ou parte do nome do cliente")
			@PathVariable String nome) {
		return new ResponseEntity<>(clienteService.buscarClientesByContainsNome(nome), HttpStatus.OK);
	}

	@DeleteMapping("excluir/{id}")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Excluir cliente", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente excluído com sucesso"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Cliente não cadastrado")
		})
	public ResponseEntity<?> excluirCliente(
			@ApiParam(name = "id", value = "Id do registro no banco de dados")
			@PathVariable("id") Long id) {
		clienteService.excluirClienteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
