package com.dev.gslcliente.service;

import java.util.List;

import javax.validation.Valid;

import com.dev.gslcliente.entities.Cliente;

public interface ClienteService {

	Cliente cadastrarCliente(@Valid Cliente cliente);
	
	List<Cliente> buscarClientes();
	
	Cliente buscarClienteById(Long id);
	
	List<Cliente> buscarClientesByNome(String nome);
	
	List<Cliente> buscarClientesByContainsNome(String nome);
	
	void atualizarCliente(@Valid Cliente cliente);
	
	void excluirClienteById(Long id);
	
}
