package com.dev.gslentrega.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.gslentrega.entities.Carga;
import com.dev.gslentrega.entities.EnderecoDestino;
import com.dev.gslentrega.entities.EnderecoOrigem;
import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.enums.StatusEntrega;
import com.dev.gslentrega.enums.StatusPagamento;
import com.dev.gslentrega.enums.TipoDocumento;
import com.dev.gslentrega.enums.UF;
import com.dev.gslentrega.errors.ClienteNotFoundException;
import com.dev.gslentrega.errors.EntregaNotFoundException;
import com.dev.gslentrega.feignclients.ClienteFeignClient;
import com.dev.gslentrega.repositories.EntregaRepository;
import com.dev.gslentrega.request.CargaRequest;
import com.dev.gslentrega.request.EntregaRequest;
import com.dev.gslentrega.response.Cliente;
import com.dev.gslentrega.utils.RandomUtils;

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

	private static final long START_RANDOM_NUMBER = 100000000000L;
	private static final long END_RANDOM_NUMBER = 999999999999L;	
	
	@Override
	public Entrega buscarEntregaById(Long id) {
		verificaSeEntregaExisteById(id);
		return entregaRepository.findById(id).get();
	}

	@Override
	public Entrega buscarEntregaByCodigoSolicitacao(Long codigoSolicitacao) {
		verificaSeEntregaExisteByCodigoSolicitacao(codigoSolicitacao);
		return entregaRepository.findByCodigoSolicitacao(codigoSolicitacao);
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
		entrega.setCodigoSolicitacao(getNovoCodigoSolicitacao(START_RANDOM_NUMBER, END_RANDOM_NUMBER));
		entrega.setCnpjCliente(entregaRequest.getCnpjCliente());
		entrega.setTipoDocumentoDestinatario(entregaRequest.getTipoDocumentoDestinatario() == 1 
				? TipoDocumento.CPF : TipoDocumento.CNPJ);
		entrega.setDocumentoDestinatario(entregaRequest.getDocumentoDestinatario());
		entrega.setEnderecoOrigem(enderecoOrigem);
		entrega.setEnderecoDestino(enderecoDestino);
		entrega.setDataSolicitacao(LocalDateTime.now());
		entrega.setDataPrevisao(null); // Calcular
		entrega.setStatusPagamento(StatusPagamento.PENDENTE);
		entrega.setStatusEntrega(StatusEntrega.EM_ANALISE);
		entrega.setValor(null); //calcular
		entrega.setCargas(getCargasRequest(entregaRequest)); //grava a lista do request
		entrega.setValorFrete(entrega.getValorFretePeso()); //Calcular
		entrega.setNaturezaPrestacao(null); //todo
		entrega.setSituacaoTributaria(null);//todo
		entrega.setBaseCalculoImposto(null); //valor total do servico (calcular)
		entrega.setAliquotaIcms(null); //definir
		entrega.setValorIcms(null);//calcular
		entrega.setObservacoes(null);

		return entregaRepository.save(entrega);
	}

	private List<Carga> getCargasRequest(EntregaRequest entregaRequest) {
		List<CargaRequest> cargaRequests = entregaRequest.getCargas();
		if (cargaRequests == null) {
			return null;
		}
		
		List<Carga> list = new ArrayList<>(cargaRequests.size());
		for (CargaRequest cargaRequest : cargaRequests) {
			Carga carga = new Carga();
			carga.setEspecie(cargaRequest.getEspecie());
			carga.setNatureza(cargaRequest.getNatureza());
			carga.setNotaFiscal(cargaRequest.getNotaFiscal());
			carga.setPeso(cargaRequest.getPeso());
			carga.setQuantidade(cargaRequest.getQuantidade());
			carga.setVolume(cargaRequest.getVolume());
			carga.setValor(cargaRequest.getValor());
			carga.setNotaFiscal(cargaRequest.getNotaFiscal());
			list.add(carga);
		}
		return list;
	}

	private void verificaSeClienteExisteByCnpj(Long cnpjCliente) {
		if (getCliente(cnpjCliente) == null) {
			throw new ClienteNotFoundException("Cliente não cadastrado para o cnpj : " + cnpjCliente);
		}
		
	}

	private boolean codigoSolicitacaoExists(long codigoSolicitacao) {
		return entregaRepository.findByCodigoSolicitacao(codigoSolicitacao) != null ? true : false;
	}
	
	private Long getNovoCodigoSolicitacao(long startRandomNumber, long endRandomNumber) {
		long newCode;
		
		do {
			newCode = RandomUtils.getRandomNumber(startRandomNumber, endRandomNumber);
		} while (codigoSolicitacaoExists(newCode));
		return newCode;
	}

	private void verificaSeEntregaExisteById(Long id) {
		if (!entregaExisteById(id))
			throw new EntregaNotFoundException("Entrega não encontrada para o ID: " + id);
	}

	private boolean entregaExisteById(Long id) {
		return(entregaRepository.findById(id).isPresent());
	}

	private void verificaSeEntregaExisteByCodigoSolicitacao(Long codigoSolicitacao) {
		if (!entregaExisteByCodigoSolicitacao(codigoSolicitacao))
			throw new EntregaNotFoundException("Entrega não encontrada para o código de solicitação: " + codigoSolicitacao);
	}

	private boolean entregaExisteByCodigoSolicitacao(Long codigoSolicitacao) {
		return entregaRepository.findByCodigoSolicitacao(codigoSolicitacao) != null ? true : false;
	}

}
