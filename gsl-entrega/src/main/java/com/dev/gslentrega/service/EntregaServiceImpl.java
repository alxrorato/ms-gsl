package com.dev.gslentrega.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dev.gslentrega.utils.MockUtils;
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
	private static final String SITUACAO_TRIBUTARIA = "00 - Tributação normal ICMS";
	private static final Double PESO_CUBADO_EM_KG_PARA_1M3 = 300.0;
	private static final Double ALIQUOTA_ICMS = 12.0;
	
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
		entrega.setDataPrevisao(MockUtils.getDataPrevisaoEntrega(entrega.getDataSolicitacao(), enderecoOrigem, 
				enderecoDestino, MockUtils.getDistancia()));
		entrega.setStatusPagamento(StatusPagamento.PENDENTE);
		entrega.setStatusEntrega(StatusEntrega.EM_ANALISE);
		entrega.setCargas(getCargasRequest(entregaRequest)); //grava a lista do request
		entrega.setValorFrete(getValorFretePeso(entrega.getCargas()));
		entrega.setValor(entrega.getValorFrete()); // será igual ao valor do frete
		entrega.setNaturezaPrestacao(entregaRequest.getNaturezaPrestacao());
		entrega.setSituacaoTributaria(SITUACAO_TRIBUTARIA);
		entrega.setBaseCalculoImposto(null); //valor total do servico (calcular)
		entrega.setAliquotaIcms(ALIQUOTA_ICMS); //12% da base de cálculo
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

	/* Calcular:
	 * 1) Peso cubado: considerar que 1m3 <==> 300kg
	 * 				 volumeTotalCarga * fator de cubagem (300 kg/m3)
	 * 2) Valor Frete peso: 1,50 * (MAX(peso total da Carga, peso Cubado))
	 * Ex.: VolumeTotalCarga = 20m3, pesoTotalCarga=500Kg, PrecoPorkg=1,50
	 *      Peso Cubado = 20 * 300 = 6000Kg. pesoCubado ficou maior que o pesoTotalCarga
	 *      ValorFretePeso = 1,50 * 6000 = 9.000 
	 */
	//TODO
	private Double getValorFretePeso(List<Carga> cargas) {
		Double pesoTotal = getPesoTotalCarga(cargas);
		Double volumeTotal = getVolumeTotalCarga(cargas);
		Double precoPorKg = MockUtils.getPrecoPorKg(pesoTotal);
		Double pesoCubado = getPesoCubadoCarga(volumeTotal);
		return precoPorKg * Math.max(pesoTotal, pesoCubado);
	}

	private Double getPesoTotalCarga(List<Carga> cargas) {
		return cargas.stream().mapToDouble(Carga::getPeso).sum();
	}
	
	private Double getVolumeTotalCarga(List<Carga> cargas) {
		return cargas.stream().mapToDouble(Carga::getVolume).sum();
	}
	
	private Double getPesoCubadoCarga(Double volume) {
		return volume * PESO_CUBADO_EM_KG_PARA_1M3;
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
