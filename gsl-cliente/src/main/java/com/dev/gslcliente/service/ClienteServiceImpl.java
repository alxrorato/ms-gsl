package com.dev.gslcliente.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.gslcliente.entities.Cliente;
import com.dev.gslcliente.entities.Endereco;
import com.dev.gslcliente.enums.StatusCliente;
import com.dev.gslcliente.enums.UF;
import com.dev.gslcliente.errors.ClienteInvalidIdException;
import com.dev.gslcliente.errors.ClienteJaExisteException;
import com.dev.gslcliente.errors.ClienteNotFoundException;
import com.dev.gslcliente.request.ClienteRequest;
import com.dev.gslcliente.service.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Cliente cadastrarCliente(@Valid ClienteRequest clienteRequest) {
		
		verificaSeClienteExisteByCnpj(clienteRequest.getCnpj());
		
		Cliente cliente = new Cliente();
		cliente.setCnpj(clienteRequest.getCnpj());
		cliente.setEmail(clienteRequest.getEmail());
		cliente.setNomeComercial(clienteRequest.getNomeComercial());
		cliente.setRazaoSocial(clienteRequest.getRazaoSocial());
		cliente.setStatus(StatusCliente.A);
		cliente.setDataInclusao(LocalDateTime.now());
		cliente.setTelefone(clienteRequest.getTelefone());
		
		UF uf = UF.getUFBySigla(clienteRequest.getEndereco().getUf().toUpperCase());
		Endereco endereco = new Endereco();
		endereco.setLogradouro(clienteRequest.getEndereco().getLogradouro());
		endereco.setNumero(clienteRequest.getEndereco().getNumero());
		endereco.setComplemento(clienteRequest.getEndereco().getComplemento());
		endereco.setBairro(clienteRequest.getEndereco().getBairro());
		endereco.setCidade(clienteRequest.getEndereco().getCidade());
		endereco.setUf(uf);
		endereco.setCep(clienteRequest.getEndereco().getCep());
		
		cliente.setEndereco(endereco);

		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> buscarClientes() {
		Iterable<Cliente> it = clienteRepository.findAll();

		List<Cliente> clientes = new ArrayList<Cliente>();

		for (Cliente e : it) {
			clientes.add(e);
		}

		return clientes;
	}

	@Override
	public Cliente buscarClienteById(Long id) {
		verificaSeClienteExisteById(id);
		return clienteRepository.findById(id).get();
	}

	@Override
	public void atualizarCliente(@Valid Cliente cliente) {
		verificaSeClienteExisteById(cliente.getId());
		clienteRepository.save(cliente);
	}

	@Override
	public void excluirClienteById(Long id) {
		verificaSeClienteExisteById(id);
		clienteRepository.deleteById(id);
	}

	@Override
	public Cliente buscarClienteByCnpj(Long cnpj) {
		List<Cliente> clientes = clienteRepository.findByCnpj(cnpj);
		return (!clientes.isEmpty() && clientes.size() > 0) ? clientes.get(0) : null;
	}

	@Override
	public Cliente buscarClienteByCnpj(Long cnpj, boolean geraExceptionSeNaoExistir) {
		List<Cliente> clientes = clienteRepository.findByCnpj(cnpj);
		if (clientes.isEmpty() || clientes.size() == 0) {
			throw new ClienteNotFoundException("Cliente não encontrado para o CNPJ: " + cnpj);
		}
		return clientes.get(0);
	}
	
	@Override
	public List<Cliente> buscarClientesByNome(String nome) {
		return clienteRepository.findByNomeComercial(nome);
	}

	@Override
	public List<Cliente> buscarClientesByContainsNome(String nome) {
		return clienteRepository.findByNomeComercialIgnoreCaseContaining(nome);
	}

	private boolean clienteExisteById(Long id) {
		return(clienteRepository.findById(id).isPresent());
	}

	private void verificaSeClienteExisteById(Long id) {
		if (id <= 0) {
			throw new ClienteInvalidIdException("Identificador inválido:" + id);
		}
		if (!clienteExisteById(id))
			throw new ClienteNotFoundException("Cliente não encontrado para o ID: " + id);
	}

	private boolean clienteExisteByCnpj(Long cnpj) {
		return(buscarClienteByCnpj(cnpj) != null ? true : false);
	}

	private void verificaSeClienteExisteByCnpj(Long cnpj) {
		if (cnpj <= 0) {
			throw new ClienteInvalidIdException("Cnpj inválido:" + cnpj);
		}
		if (clienteExisteByCnpj(cnpj)) {
			throw new ClienteJaExisteException("Já existe cliente cadastrado para o cnpj : " + cnpj);
		}
	}
}
