package com.dev.gslcliente.service;

import java.util.List;

import javax.validation.Valid;

import com.dev.gslcliente.entities.Cliente;
import com.dev.gslcliente.request.ClienteRequest;

public interface ClienteService {

	Cliente cadastrarCliente(@Valid ClienteRequest cliente);
	
	List<Cliente> buscarClientes();
	
	Cliente buscarClienteById(Long id);

	Cliente buscarClienteByCnpj(Long cnpj);
	
	Cliente buscarClienteByCnpj(Long cnpj, boolean geraExceptionSeNaoExistir);
	
	List<Cliente> buscarClientesByNome(String nome);
	
	List<Cliente> buscarClientesByContainsNome(String nome);
	
	void atualizarCliente(@Valid Cliente cliente);
	
	void excluirClienteById(Long id);

}
