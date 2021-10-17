package com.dev.gslentrega.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
import com.dev.gslentrega.response.ParceiraResponse;
import com.dev.gslentrega.utils.MockUtils;
import com.dev.gslentrega.utils.RandomUtils;
import com.dev.gslentrega.utils.UFUtils;

@Service
public class EntregaServiceImpl implements EntregaService {

	@Value("${gsl-parceira.host}")
	private String parceiraHost;
	
	@Value("${cnpj.boaentrega}")
	private String cnpjBoaEntrega;

	@Autowired
	private RestTemplate restTemplate;
	
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
	private static final Double TAXA_SEGURO = 0.03;
	private static final Double IOF = 7.38;
	
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
		entrega.setDistanciaTotal(MockUtils.getDistancia());
		entrega.setDataPrevisao(MockUtils.getDataPrevisaoEntrega(entrega.getDataSolicitacao(), enderecoOrigem, 
				enderecoDestino, entrega.getDistanciaTotal()));
		entrega.setStatusPagamento(StatusPagamento.PENDENTE);
		entrega.setStatusEntrega(StatusEntrega.EM_ANALISE);
		entrega.setCargas(getCargasRequest(entregaRequest)); //grava a lista do request
		entrega.setValorFrete(getValorFretePeso(entrega.getCargas()));
		entrega.setValorTotal(entrega.getValorFrete()); // será igual ao valor do frete
		entrega.setNaturezaPrestacao(entregaRequest.getNaturezaPrestacao());
		entrega.setSituacaoTributaria(SITUACAO_TRIBUTARIA);
		entrega.setBaseCalculoImposto(entrega.getValorTotal()); // será igual ao valor total do servico
		entrega.setAliquotaIcms(ALIQUOTA_ICMS); //12% da base de cálculo
		entrega.setValorIcms(getValorIcmsCalculado(entrega.getBaseCalculoImposto(), ALIQUOTA_ICMS));
		entrega.setValorTotalSeguroCarga(getValorTotalSeguroCarga(entrega.getCargas()));
		entrega.setObservacoes(entregaRequest.getObservacoes());
		entrega.setEntregaEmParceria(false);
		
		if (UFUtils.NORTE.equals(UFUtils.getRegiaoByUf(entrega.getEnderecoDestino().getUf().getSigla()))) {
			ParceiraResponse parceiraResponse = getParceriaEntrega();
			if (parceiraResponse.isSolicitacaoAceita()) {
				entrega.setEntregaEmParceria(true);
				entrega.setCnpjParceira(parceiraResponse.getCnpj());
				entrega.setRazaoSocialParceira(parceiraResponse.getRazaoSocial());
				entrega.setNomeComercialParceira(parceiraResponse.getNomeComercial());
			}
		}

		return entregaRepository.save(entrega);
	}

	private ParceiraResponse getParceriaEntrega() {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("cnpjSolicitante", cnpjBoaEntrega);
		
		ParceiraResponse parceiraResponse = restTemplate.getForObject(parceiraHost + "/parceira/solicitarParceria/{cnpjSolicitante}", ParceiraResponse.class, uriVariables);
		return parceiraResponse;
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
	
	private Double getValorIcmsCalculado(Double valor, Double aliquota) {
		return valor * aliquota / 100;
	}

	/* Cálculo do seguro da carga
	Consideramos uma taxa fixa de 0,03% .

	Valor da IS = R$ 2.000.000,00

	Resultado:
	Prêmio Líquido = R$ 2.000.000,00 * 0,03% = R$ 600,00, portanto abaixo do prêmio mínimo , será cobrado R$ 600,00.
	Prêmio Total = Prêmio Liquido + IOF ( 7,38%);
	IOF = 600,00 * 7,38% = 44,28
	Prêmio Total = R$ 600,00 + R$ 44,28 = R$ 644,28
	Total do Prêmio Bruto = RCTR-C (acidente) + RCF-DC (carga) = 644,28 + 644,28
	Obs.: para efeito da Poc assumiremos que as regras de cálculo para acidente e roubo serão as mesmas  
	*/
	private Double getValorTotalSeguroCarga(List<Carga> cargas) {
		Double valorTotalCarga = getValorTotalCarga(cargas);
		Double valorPremioLiquido = valorTotalCarga * TAXA_SEGURO;
		Double valorIof = valorPremioLiquido * IOF/100;
		Double valorPremioTotal = valorPremioLiquido + valorIof;
		return valorPremioTotal * 2.0; //multiplica por 2 porque serão 2 seguros: RCTR-C (acidente) + RCF-DC (carga)
	}
	
	private Double getValorTotalCarga(List<Carga> cargas) {
		return cargas.stream().mapToDouble(Carga::getValor).sum();
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

	@Override
	public void atualizarEntregaByCodigoSolicitacao(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		entrega.setDataStatusEntrega(LocalDateTime.now());
		entrega.setDistanciaPercorrida(entrega.getDistanciaTotal() - entrega.getDistanciaPercorrida());
		entregaRepository.save(entrega);
	}

}
