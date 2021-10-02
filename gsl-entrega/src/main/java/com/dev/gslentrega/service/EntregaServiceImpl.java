package com.dev.gslentrega.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.gslentrega.entities.Endereco;
import com.dev.gslentrega.entities.EnderecoDestino;
import com.dev.gslentrega.entities.EnderecoOrigem;
import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.enums.StatusEntrega;
import com.dev.gslentrega.enums.TipoDocumento;
import com.dev.gslentrega.enums.UF;
import com.dev.gslentrega.errors.ClienteNotFoundException;
import com.dev.gslentrega.feignclients.ClienteFeignClient;
import com.dev.gslentrega.repositories.EntregaRepository;
import com.dev.gslentrega.request.EntregaRequest;
import com.dev.gslentrega.response.Cliente;

@Service
public class EntregaServiceImpl implements EntregaService {

	@Autowired
	private ClienteFeignClient clienteFeignClient;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Override
	public List<Entrega> buscarEntregas() {
		return entregaRepository.findAll();
	}

	@Override
	public Entrega buscarEntregaById(Long id) {
		return entregaRepository.findById(id).get();
	}

	public boolean clienteExists(Long cnpj) {
		Cliente cliente = clienteFeignClient.buscarClientesByCnpj(cnpj).getBody();
		return (cliente != null ? true : false);
	}
	
	@Override
	public Cliente getCliente(Long cnpj) {
		Cliente cliente = clienteFeignClient.buscarClientesByCnpj(cnpj).getBody();
		return cliente;
	}

	@Override
	public Entrega cadastrarEntrega(@Valid EntregaRequest entregaRequest) {

		verificaSeClienteExisteByCnpj(entregaRequest.getCnpjCliente());
		

		UF ufOrigem = UF.getUFBySigla(entregaRequest.getEnderecoOrigem().getUf().toUpperCase());
		EnderecoOrigem enderecoOrigem = new EnderecoOrigem();
		enderecoOrigem.setLogradouro(entregaRequest.getEnderecoOrigem().getLogradouro());
		enderecoOrigem.setNumero(entregaRequest.getEnderecoOrigem().getNumero());
		enderecoOrigem.setComplemento(entregaRequest.getEnderecoOrigem().getComplemento());
		enderecoOrigem.setBairro(entregaRequest.getEnderecoOrigem().getBairro());
		enderecoOrigem.setCidade(entregaRequest.getEnderecoOrigem().getCidade());
		enderecoOrigem.setUf(ufOrigem);
		enderecoOrigem.setCep(entregaRequest.getEnderecoOrigem().getCep());
		
		UF ufDestino = UF.getUFBySigla(entregaRequest.getEnderecoDestino().getUf().toUpperCase());
		EnderecoDestino enderecoDestino = new EnderecoDestino();
		enderecoDestino.setLogradouro(entregaRequest.getEnderecoDestino().getLogradouro());
		enderecoDestino.setNumero(entregaRequest.getEnderecoDestino().getNumero());
		enderecoDestino.setComplemento(entregaRequest.getEnderecoDestino().getComplemento());
		enderecoDestino.setBairro(entregaRequest.getEnderecoDestino().getBairro());
		enderecoDestino.setCidade(entregaRequest.getEnderecoDestino().getCidade());
		enderecoDestino.setUf(ufDestino);
		enderecoDestino.setCep(entregaRequest.getEnderecoDestino().getCep());

		Entrega entrega = new Entrega();
		entrega.setCodigoSolicitacao(null); //Gerar
		entrega.setCnpjCliente(entregaRequest.getCnpjCliente());
		entrega.setTipoDocumentoDestinatario(entregaRequest.getTipoDocumentoDestinatario() == 1 
				? TipoDocumento.CPF : TipoDocumento.CNPJ);
		entrega.setDocumentoDestinatario(entregaRequest.getDocumentoDestinatario());
		entrega.setEnderecoOrigem(enderecoOrigem);
		entrega.setEnderecoDestino(enderecoDestino);
		entrega.setDataSolicitacao(LocalDateTime.now());
		entrega.setDataPrevisao(null); // Calcular
		entrega.setStatusEntrega(StatusEntrega.EM_ANALISE);
		entrega.setValor(null); //calcular
		entrega.setCarga(null); //mudar p/ oneToMany, pois pode ser uma lista

		return entregaRepository.save(entrega);
	}

	private void verificaSeClienteExisteByCnpj(Long cnpjCliente) {
		if (getCliente(cnpjCliente) == null) {
			throw new ClienteNotFoundException("Cliente não cadastrado para o cnpj : " + cnpjCliente);
		}
		
	}
}
