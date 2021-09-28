package com.dev.gslcliente.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.gslcliente.entities.Cliente;
import com.dev.gslcliente.enums.StatusCliente;
import com.dev.gslcliente.errors.ClienteInvalidIdException;
import com.dev.gslcliente.errors.ClienteNotFoundException;
import com.dev.gslcliente.request.ClienteRequest;
import com.dev.gslcliente.service.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public Cliente cadastrarCliente(@Valid ClienteRequest clienteRequest) {
		Cliente cliente = new Cliente();
		cliente.setCnpj(clienteRequest.getCnpj());
		cliente.setDataInclusao(clienteRequest.getDataInclusao());
		cliente.setEmail(clienteRequest.getEmail());
		cliente.setNomeComercial(clienteRequest.getNomeComercial());
		cliente.setRazaoSocial(clienteRequest.getRazaoSocial());
		if (!StringUtils.hasText(clienteRequest.getStatus())) {
			cliente.setStatus(StatusCliente.valueOf(clienteRequest.getStatus().toUpperCase()));
		}
		cliente.setTelefone(clienteRequest.getTelefone());
		
		return repository.save(cliente);
	}

	@Override
	public List<Cliente> buscarClientes() {
		Iterable<Cliente> it = repository.findAll();

		List<Cliente> clientes = new ArrayList<Cliente>();

		for (Cliente e : it) {
			clientes.add(e);
		}

		return clientes;
	}

	@Override
	public Cliente buscarClienteById(Long id) {
		verificaSeClienteExiste(id);
		return repository.findById(id).get();
	}

	@Override
	public void atualizarCliente(@Valid Cliente cliente) {
		verificaSeClienteExiste(cliente.getId());
		repository.save(cliente);
	}

	@Override
	public void excluirClienteById(Long id) {
		verificaSeClienteExiste(id);
		repository.deleteById(id);
	}

	@Override
	public List<Cliente> buscarClientesByNome(String nome) {
		return repository.findByNomeComercial(nome);
	}

	@Override
	public List<Cliente> buscarClientesByContainsNome(String nome) {
		return repository.findByNomeComercialIgnoreCaseContaining(nome);
	}

	private boolean clienteExiste(Long id) {
		return(repository.findById(id).isPresent());
	}

	private void verificaSeClienteExiste(Long id) {
		if (id <= 0) {
			throw new ClienteInvalidIdException("Identificador inválido:" + id);
		}
		if (!clienteExiste(id))
			throw new ClienteNotFoundException("Cliente não encontrado para o ID: " + id);
	}

}
