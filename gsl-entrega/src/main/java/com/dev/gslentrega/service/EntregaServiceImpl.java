package com.dev.gslentrega.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.dev.gslentrega.enums.TipoModal;
import com.dev.gslentrega.enums.UF;
import com.dev.gslentrega.errors.ClienteNotFoundException;
import com.dev.gslentrega.errors.EntregaNotFoundException;
import com.dev.gslentrega.errors.OperacaoNaoEfetuadaException;
import com.dev.gslentrega.feignclients.ClienteFeignClient;
import com.dev.gslentrega.repositories.EntregaRepository;
import com.dev.gslentrega.request.CalculoFreteRequest;
import com.dev.gslentrega.request.CargaRequest;
import com.dev.gslentrega.request.EntregaRequest;
import com.dev.gslentrega.request.SolicitacaoRequest;
import com.dev.gslentrega.response.AndamentoEntregaResponse;
import com.dev.gslentrega.response.CalculoFreteResponse;
import com.dev.gslentrega.response.CancelamentoResponse;
import com.dev.gslentrega.response.Cliente;
import com.dev.gslentrega.response.ComponenteValor;
import com.dev.gslentrega.response.ComponentesValorPrestacaoServico;
import com.dev.gslentrega.response.ConfirmacaoEntregaResponse;
import com.dev.gslentrega.response.Dacte;
import com.dev.gslentrega.response.DadosAtorCte;
import com.dev.gslentrega.response.DadosSeguroCarga;
import com.dev.gslentrega.response.EmissaoCteResponse;
import com.dev.gslentrega.response.EnderecoAtorCte;
import com.dev.gslentrega.response.LocalizacaoCarga;
import com.dev.gslentrega.response.NaturezaPrestacao;
import com.dev.gslentrega.response.OperacaoEtapaResponse;
import com.dev.gslentrega.response.PagamentoResponse;
import com.dev.gslentrega.response.ParceiraResponse;
import com.dev.gslentrega.utils.GeneralUtils;
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
	
	private static final long START_RANDOM_NUMBER_SOLICITACAO = 100000000000L;
	private static final long END_RANDOM_NUMBER_SOLICITACAO = 999999999999L;	
	private static final long START_RANDOM_NUMBER_APOLICE = 1000000000L;
	private static final long END_RANDOM_NUMBER_APOLICE = 9999999999L;	
	private static final long START_RANDOM_NUMBER_AVERBACAO = 100000L;
	private static final long END_RANDOM_NUMBER_AVERBACAO = 9999999L;	
	private static final long START_RANDOM_NUMBER_PROTOCOLO_AUTORIZACAO_USO = 100000000000000L;
	private static final long END_RANDOM_NUMBER_PROTOCOLO_AUTORIZACAO_USO = 999999999999999L;	
	private static final String SITUACAO_TRIBUTARIA = "00 - Tributação normal ICMS";
	private static final BigDecimal PESO_CUBADO_EM_KG_PARA_1M3 = new BigDecimal(300.0);
	private static final BigDecimal ALIQUOTA_ICMS = new BigDecimal(12.0);
	private static final BigDecimal TAXA_SEGURO = new BigDecimal(0.03);
	private static final BigDecimal IOF = new BigDecimal(7.38);
	private static final BigDecimal LIMITE_LATITUDE_LONGITUDE = new BigDecimal(99);
	private static final String PESO_CUBADO = "peso cubado";
	private static final String PESO_TOTAL = "peso total";
	private static final String TITULO_DACTE = "Documento Auxiliar do Conhecimento de Transporte Eletrônico";
	private static final int LIMIT_RANDOM_MODELO_DACTE = 60;
	private static final int SERIE_DACTE = 1;
	private static final String FOLHA_DACTE = "1/1";
	private static final String TEXTO_CHAVE_ACESSO = "Consulta de autenticidade no portal nacional do CT-2, no site da Sefaz Autorizadora, ou em http://cte.fazenda.gov.br";
	private static final int TIPO_ENDERECO_ORIGEM = 1;
	private static final int TIPO_ENDERECO_DESTINO = 2;
	private static final String FRETE_VALOR = "Frete Valor";
	
	@Override
	public List<Entrega> buscarEntregas() {
		return entregaRepository.findAll();
	}

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
		entrega.setCodigoSolicitacao(getNovoCodigoSolicitacao(START_RANDOM_NUMBER_SOLICITACAO, END_RANDOM_NUMBER_SOLICITACAO));
		entrega.setCnpjCliente(entregaRequest.getCnpjCliente());
		entrega.setTipoDocumentoDestinatario(GeneralUtils.CPF.equals(entregaRequest.getTipoDocumentoDestinatario()) 
				? TipoDocumento.CPF : TipoDocumento.CNPJ);
		entrega.setDocumentoDestinatario(entregaRequest.getDocumentoDestinatario());
		entrega.setEnderecoOrigem(enderecoOrigem);
		entrega.setEnderecoDestino(enderecoDestino);
		entrega.setDataSolicitacao(LocalDateTime.now());
		entrega.setDistanciaTotal(MockUtils.getDistancia(MockUtils.DISTANCIA_LIMITE));
		entrega.setDistanciaPercorrida(GeneralUtils.ZERO);
		entrega.setDataPrevisao(MockUtils.getDataPrevisaoEntrega(entrega.getDataSolicitacao(), enderecoOrigem, 
				enderecoDestino, entrega.getDistanciaTotal()));
		entrega.setStatusPagamento(StatusPagamento.PENDENTE);
		entrega.setStatusEntrega(StatusEntrega.SOLICITACAO);
		entrega.setDataStatusEntrega(LocalDateTime.now());
		entrega.setCargas(getCargasRequest(entregaRequest)); //grava a lista do request
		entrega.setValorFrete(getValorFretePeso(entrega.getCargas()));
		entrega.setValorTotal(entrega.getValorFrete()); // será igual ao valor do frete
		entrega.setNaturezaPrestacao(entregaRequest.getNaturezaPrestacao());
		entrega.setSituacaoTributaria(SITUACAO_TRIBUTARIA);
		entrega.setBaseCalculoImposto(entrega.getValorTotal()); // será igual ao valor total do servico
		entrega.setAliquotaIcms(ALIQUOTA_ICMS); //12% da base de cálculo
		entrega.setValorIcms(getValorIcmsCalculado(entrega.getBaseCalculoImposto(), ALIQUOTA_ICMS));
		entrega.setValorTotalSeguroCarga(getValorTotalSeguroCarga(entrega.getCargas()));
		entrega.setNumeroApoliceSeguroCarga(RandomUtils.getRandomNumber(START_RANDOM_NUMBER_APOLICE, END_RANDOM_NUMBER_APOLICE));
		entrega.setNumeroAverbacaoSeguroCarga(RandomUtils.getRandomNumber(START_RANDOM_NUMBER_AVERBACAO, END_RANDOM_NUMBER_AVERBACAO));

		entrega.setTipoModal(TipoModal.RODOVIARIO);
		entrega.setCteEmitido(false);
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

		return getListCargaByListCargaRequest(entregaRequest.getCargaRequests());
	}

	private List<Carga> getListCargaByListCargaRequest(List<CargaRequest> cargaRequests) {
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
	private BigDecimal getValorFretePeso(List<Carga> cargas) {
		BigDecimal pesoTotal = getPesoTotalCarga(cargas);
		BigDecimal volumeTotal = getVolumeTotalCarga(cargas);
		BigDecimal precoPorKg = MockUtils.getPrecoPorKg(pesoTotal);
		BigDecimal pesoCubado = getPesoCubadoCarga(volumeTotal);
		return precoPorKg.multiply(pesoTotal.max(pesoCubado));
	}

	// Mesma calculo do método acima, mas este recebe como parâmetro os valores que compõe o cálculo
	private BigDecimal getValorFretePeso(BigDecimal pesoTotal, BigDecimal precoPorKg, BigDecimal pesoCubado) {
		return precoPorKg.multiply(pesoTotal.max(pesoCubado));
	}

	private BigDecimal getPesoTotalCarga(List<Carga> cargas) {
		return cargas.stream().map(Carga::getPeso).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private BigDecimal getVolumeTotalCarga(List<Carga> cargas) {
		return cargas.stream().map(Carga::getVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private BigDecimal getPesoCubadoCarga(BigDecimal volume) {
		return volume.multiply(PESO_CUBADO_EM_KG_PARA_1M3);
	}
	
	private BigDecimal getValorIcmsCalculado(BigDecimal valor, BigDecimal aliquota) {
		return valor.multiply(aliquota).divide(new BigDecimal(100));
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
	private BigDecimal getValorTotalSeguroCarga(List<Carga> cargas) {
		BigDecimal valorTotalCarga = getValorTotalCarga(cargas);
		BigDecimal valorPremioLiquido = valorTotalCarga.multiply(TAXA_SEGURO);
		BigDecimal valorIof = valorPremioLiquido.multiply(IOF).divide(GeneralUtils.CEM);
		BigDecimal valorPremioTotal = valorPremioLiquido.add(valorIof);
		//multiplica por 2 porque serão 2 seguros: RCTR-C (acidente) + RCF-DC (carga)
		return valorPremioTotal.multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);
	}
	
	private BigDecimal getValorTotalCarga(List<Carga> cargas) {
		return cargas.stream().map(Carga::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
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
	public void atualizarEntrega(SolicitacaoRequest solicitacaoRequest) {
		atualizarPercurso(solicitacaoRequest.getCodigoSolicitacao());
	}

	@Override
	public AndamentoEntregaResponse findProgressByRequestCode(Long codigoSolicitacao) {
		atualizarPercurso(codigoSolicitacao);
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		AndamentoEntregaResponse andamentoEntregaResponse = new AndamentoEntregaResponse();
		andamentoEntregaResponse.setCodigoSolicitacao(codigoSolicitacao);
		andamentoEntregaResponse.setDistanciaTotal(entrega.getDistanciaTotal());
		andamentoEntregaResponse.setDistanciaPercorrida(entrega.getDistanciaPercorrida());
		andamentoEntregaResponse.setDistanciaApercorrer(entrega.getDistanciaTotal().subtract(entrega.getDistanciaPercorrida()));
		andamentoEntregaResponse.setPercentualPercorrido(GeneralUtils.percentual(entrega.getDistanciaTotal(), 
				entrega.getDistanciaPercorrida()));
		andamentoEntregaResponse.setPercentualAPercorrer(GeneralUtils.percentual(entrega.getDistanciaTotal(), 
				andamentoEntregaResponse.getDistanciaApercorrer()));
		andamentoEntregaResponse.setStatus(entrega.getStatusEntrega().getDescricao());
		andamentoEntregaResponse.setPrevisaoEntrega(montaTextoPrevisaoEntrega(entrega.getDataPrevisao(), 
				entrega.getDataAlteracao() != null ? entrega.getDataAlteracao() : LocalDateTime.now()));
		
		LocalizacaoCarga localizacao = new LocalizacaoCarga(MockUtils.getLatitudeLongitude(LIMITE_LATITUDE_LONGITUDE),
				MockUtils.getLatitudeLongitude(LIMITE_LATITUDE_LONGITUDE));
		
		andamentoEntregaResponse.setLocalizacaoCarga(localizacao);
		
		return andamentoEntregaResponse;
	}

	private String montaTextoPrevisaoEntrega(LocalDateTime dataPrevisao, LocalDateTime dataConsulta) {
		long dias = ChronoUnit.DAYS.between(dataConsulta.toLocalDate(), dataPrevisao.toLocalDate());
		String textoPrevisaoEntrega = "Em " + dias + " dias";
     	if (dias == 0L) {
			Duration duration = Duration.between(dataConsulta, dataPrevisao);
			textoPrevisaoEntrega = "Hoje aproximadamente daqui a " + duration.toHours() + "h" + duration.toMinutesPart();
		}
     	return textoPrevisaoEntrega;
	}

	private void atualizarPercurso(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		BigDecimal distanciaApercorrer = entrega.getDistanciaTotal().subtract(entrega.getDistanciaPercorrida());

		if (entrega.getStatusEntrega().getCodigo() >= StatusEntrega.TRANSPORTE.getCodigo()
				&& entrega.getStatusEntrega().getCodigo() < StatusEntrega.FINALIZADA.getCodigo()
				&& distanciaApercorrer.compareTo(GeneralUtils.UM) > 0) {
			
			entrega.setDistanciaPercorrida(entrega.getDistanciaPercorrida().add(
					MockUtils.getDistancia(entrega.getDistanciaTotal().subtract(GeneralUtils.UM)
							.subtract(entrega.getDistanciaPercorrida()))));
			
			entrega.setDataAlteracao(LocalDateTime.now());
			entregaRepository.save(entrega);
		} 
	}

	@Override
	public PagamentoResponse efetuarPagamento(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		if (StatusPagamento.CONFIRMADO.equals(entrega.getStatusPagamento())) {
			throw new OperacaoNaoEfetuadaException("Pagamento já foi efetuado.");
		}
		entrega.setStatusPagamento(StatusPagamento.CONFIRMADO);
		entrega.setDataPagamento(LocalDateTime.now());
		entrega.setDataAlteracao(LocalDateTime.now());
		entregaRepository.save(entrega);
		PagamentoResponse pagamentoResponse = new PagamentoResponse();
		pagamentoResponse.setCodigoSolicitacaoEntrega(codigoSolicitacao);
		pagamentoResponse.setDataPagamento(entrega.getDataPagamento());
		pagamentoResponse.setValorPago(entrega.getValorTotal());
		pagamentoResponse.setMensagem("Pagamento efetuado com sucesso");
		return pagamentoResponse;
	}

	@Override
	public OperacaoEtapaResponse coletarCarga(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		if (StatusEntrega.SOLICITACAO.equals(entrega.getStatusEntrega())) {
			if (StatusPagamento.PENDENTE.equals(entrega.getStatusPagamento())) {
				throw new OperacaoNaoEfetuadaException("Pagamento pendente. A coleta será iniciada somente após a confirmação do pagamento.");
			}
			alterarStatusEntrega(entrega, StatusEntrega.COLETA);
			entregaRepository.save(entrega);
		} else if (StatusEntrega.COLETA.equals(entrega.getStatusEntrega())) {
			throw new OperacaoNaoEfetuadaException("Esta etapa já foi iniciada");
		} else {
			throw new OperacaoNaoEfetuadaException("Esta etapa já foi concluída");
		}
		return new OperacaoEtapaResponse(codigoSolicitacao, entrega.getDataStatusEntrega(), "Coleta da carga efetuada");
	}

	@Override
	public OperacaoEtapaResponse efetuarRoteirizacao(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		if (StatusEntrega.COLETA.equals(entrega.getStatusEntrega())) {
			alterarStatusEntrega(entrega, StatusEntrega.ROTEIRIZACAO);
			entregaRepository.save(entrega);
		} else if (StatusEntrega.ROTEIRIZACAO.equals(entrega.getStatusEntrega())) {
			throw new OperacaoNaoEfetuadaException("Esta etapa já foi iniciada");
		} else {
			throw new OperacaoNaoEfetuadaException("Operação não efetuada. Motivo: " + entrega.getStatusEntrega().getDescricao());
		}
		return new OperacaoEtapaResponse(codigoSolicitacao, entrega.getDataStatusEntrega(), "Roteirização do transporte efetuada");
	}

	@Override
	public OperacaoEtapaResponse iniciarTransporte(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		if (StatusEntrega.ROTEIRIZACAO.equals(entrega.getStatusEntrega())) {
			alterarStatusEntrega(entrega, StatusEntrega.TRANSPORTE);
			entregaRepository.save(entrega);
		} else if (StatusEntrega.TRANSPORTE.equals(entrega.getStatusEntrega())) {
			throw new OperacaoNaoEfetuadaException("Operação não efetuada. Motivo: transporte já foi iniciado.");
		} else {
			throw new OperacaoNaoEfetuadaException("Operação não efetuada. Motivo: " + entrega.getStatusEntrega().getDescricao());
		}
		return new OperacaoEtapaResponse(codigoSolicitacao, entrega.getDataStatusEntrega(), "Transporte iniciado até o CD mais próximo do cliente final.");
	}

	@Override
	public OperacaoEtapaResponse distribuirNosCds(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		if (StatusEntrega.TRANSPORTE.equals(entrega.getStatusEntrega())) {
			alterarStatusEntrega(entrega, StatusEntrega.DISTRIBUICAO);
			entregaRepository.save(entrega);
		} else if (StatusEntrega.DISTRIBUICAO.equals(entrega.getStatusEntrega())) {
			throw new OperacaoNaoEfetuadaException("A distribuição no CD já está em andamento.");
		} else {
			throw new OperacaoNaoEfetuadaException("Operação não efetuada. Motivo: " + entrega.getStatusEntrega().getDescricao());
		}
		return new OperacaoEtapaResponse(codigoSolicitacao, entrega.getDataStatusEntrega(), "Carga sendo distribída em CD próximo do cliente final.");
	}

	@Override
	public OperacaoEtapaResponse iniciarLastMile(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		if (StatusEntrega.DISTRIBUICAO.equals(entrega.getStatusEntrega())) {
			alterarStatusEntrega(entrega, StatusEntrega.LAST_MILE);
			entregaRepository.save(entrega);
		} else if (StatusEntrega.LAST_MILE.equals(entrega.getStatusEntrega())) {
			throw new OperacaoNaoEfetuadaException("A entrega já saiu do CD e está a caminho do cliente final.");
		} else {
			throw new OperacaoNaoEfetuadaException("Operação não efetuada. Motivo: " + entrega.getStatusEntrega().getDescricao());
		}
		return new OperacaoEtapaResponse(codigoSolicitacao, entrega.getDataStatusEntrega(), "Last-Mile iniciado: a carga está a caminho do cliente final.");
	}

	@Override
	public ConfirmacaoEntregaResponse finalizarEntrega(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		if (StatusEntrega.LAST_MILE.equals(entrega.getStatusEntrega())) {
			alterarStatusEntrega(entrega, StatusEntrega.FINALIZADA);
			entregaRepository.save(entrega);
		} else if (StatusEntrega.FINALIZADA.equals(entrega.getStatusEntrega())) {
			throw new OperacaoNaoEfetuadaException("Operação não efetuada. Motivo: entrega já foi finalizada.");
		} else {
			throw new OperacaoNaoEfetuadaException("Operação não efetuada porque o transporte da entrega [" + codigoSolicitacao + "] ainda não foi iniciado.");
		}
		return new ConfirmacaoEntregaResponse(codigoSolicitacao, entrega.getDataFinalizacao(), "Mercadoria entregue ao cliente final.",
				entrega.getRecebedorEntrega());
	}

	@Override
	public CancelamentoResponse cancelarEntrega(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		if (StatusEntrega.FINALIZADA.equals(entrega.getStatusEntrega())) {
			throw new OperacaoNaoEfetuadaException("Cancelamento não permitido porque a entrega já foi finalizada. Favor contactar o atendimento ao cliente em caso de dúvidas.");
		}
		alterarStatusEntrega(entrega, StatusEntrega.FINALIZADA);
		entregaRepository.save(entrega);
		return new CancelamentoResponse(codigoSolicitacao, entrega.getDataCancelamento(), "Cancelamento efetuado com sucesso.", entrega.getMotivoCancelamento());
	}
	
	private void alterarStatusEntrega(Entrega entrega, StatusEntrega novoStatus) {
		entrega.setStatusEntrega(novoStatus);
		entrega.setDataStatusEntrega(LocalDateTime.now());
		entrega.setDataAlteracao(LocalDateTime.now());
		if (StatusEntrega.FINALIZADA.equals(entrega.getStatusEntrega())) {
			entrega.setDataFinalizacao(LocalDateTime.now());
			entrega.setRecebedorEntrega(MockUtils.getRecebedorMercadoria());
			entrega.setDistanciaPercorrida(entrega.getDistanciaTotal());
		} else if (StatusEntrega.CANCELADA.equals(entrega.getStatusEntrega())) {
			entrega.setDataCancelamento(LocalDateTime.now());
			entrega.setMotivoCancelamento(MockUtils.getMotivoCancelamentoEntrega());
		}
	}

	@Override
	public CalculoFreteResponse estimarCalculoFrete(@Valid CalculoFreteRequest calculoFreteRequest) {
		List<Carga> cargas = getListCargaByListCargaRequest(calculoFreteRequest.getCargas());
		BigDecimal pesoTotal = getPesoTotalCarga(cargas);
		BigDecimal volumeTotal = getVolumeTotalCarga(cargas);
		BigDecimal precoPorKg = MockUtils.getPrecoPorKg(pesoTotal);
		BigDecimal pesoCubado = getPesoCubadoCarga(volumeTotal);
		BigDecimal maxPeso = pesoTotal.max(pesoCubado);
		BigDecimal valorFrete = getValorFretePeso(pesoTotal, precoPorKg, pesoCubado);
		String pesoUtilizadoNoCalculo = (maxPeso.compareTo(pesoCubado) >= 0) ? PESO_CUBADO : PESO_TOTAL;
		String observacao = "No cálculo foi considerado o " + pesoUtilizadoNoCalculo + " da carga";
		return new CalculoFreteResponse(PESO_CUBADO_EM_KG_PARA_1M3, pesoTotal, volumeTotal, precoPorKg, 
				pesoCubado, valorFrete, observacao);
	}

	@Override
	public EmissaoCteResponse emitirCte(Long codigoSolicitacao) {
		Entrega entrega = buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		/* O Dacte é a Receita Federal quem devolve essas informações junto com as do CT-e */
		Dacte dacte = new Dacte();
		dacte.setTitulo(TITULO_DACTE);
		dacte.setModelo(RandomUtils.getNonZeroRandomNumber(LIMIT_RANDOM_MODELO_DACTE));
		dacte.setSerie(SERIE_DACTE);
		dacte.setFolha(FOLHA_DACTE);
		dacte.setDataHoraEmissao(LocalDateTime.now());
		dacte.setInscricaoSulframa(null);
		dacte.setChaveAcesso(MockUtils.getChaveAcesoDacte());
		dacte.setTextoChaveAcesso(TEXTO_CHAVE_ACESSO);
		dacte.setNumeroProtocoloAutorizacaoUso(
				RandomUtils.getRandomNumber(START_RANDOM_NUMBER_PROTOCOLO_AUTORIZACAO_USO, 
						END_RANDOM_NUMBER_PROTOCOLO_AUTORIZACAO_USO));
		dacte.setDataHoraGeracaoProtocolo(LocalDateTime.now());
		
		NaturezaPrestacao naturezaPrestacao = new NaturezaPrestacao();
		naturezaPrestacao.setCodigoNomeNaturezaPrestacao(entrega.getNaturezaPrestacao());
		naturezaPrestacao.setLocalInicioPrestacao(getCidadeUf(entrega, TIPO_ENDERECO_ORIGEM));
		naturezaPrestacao.setLocalTerminoPrestacao(getCidadeUf(entrega, TIPO_ENDERECO_DESTINO));
		
		DadosAtorCte dadosEmitente = GeneralUtils.getDadosBoaEntrega();

		Cliente cliente = getCliente(entrega.getCnpjCliente());
		EnderecoAtorCte enderecoRemetente = new EnderecoAtorCte();
		enderecoRemetente.setLogradouro(cliente.getEndereco().getLogradouro());
		enderecoRemetente.setNumero(cliente.getEndereco().getNumero());
		enderecoRemetente.setComplemento(cliente.getEndereco().getComplemento());
		enderecoRemetente.setBairro(cliente.getEndereco().getBairro());
		enderecoRemetente.setCidade(cliente.getEndereco().getCidade());
		enderecoRemetente.setUf(cliente.getEndereco().getUf());
		enderecoRemetente.setCep(cliente.getEndereco().getCep());
		DadosAtorCte dadosRemetente = new DadosAtorCte();
		dadosRemetente.setTipoDocumento(TipoDocumento.CNPJ);
		dadosRemetente.setCpfCnpj(entrega.getCnpjCliente());
		dadosRemetente.setNomeRazaoSocial(cliente.getRazaoSocial());
		dadosRemetente.setInscricaoEstadual(cliente.getInscricaoEstadual());
		dadosRemetente.setTelefone(cliente.getTelefone());
		dadosRemetente.setEndereco(enderecoRemetente);

		EnderecoAtorCte enderecoDestinatario = new EnderecoAtorCte();
		enderecoDestinatario.setLogradouro(entrega.getEnderecoDestino().getLogradouro());
		enderecoDestinatario.setNumero(entrega.getEnderecoDestino().getNumero());
		enderecoDestinatario.setComplemento(entrega.getEnderecoDestino().getComplemento());
		enderecoDestinatario.setBairro(entrega.getEnderecoDestino().getBairro());
		enderecoDestinatario.setCidade(entrega.getEnderecoDestino().getCidade());
		enderecoDestinatario.setUf(entrega.getEnderecoDestino().getUf());
		enderecoDestinatario.setCep(entrega.getEnderecoDestino().getCep());
		DadosAtorCte dadosDestinatario = new DadosAtorCte();
		dadosDestinatario.setTipoDocumento(entrega.getTipoDocumentoDestinatario());
		dadosDestinatario.setCpfCnpj(entrega.getDocumentoDestinatario());
		dadosDestinatario.setNomeRazaoSocial(TipoDocumento.CPF.equals(entrega.getTipoDocumentoDestinatario()) ?
				MockUtils.getNomeDestinatario() : MockUtils.getRazaoSocialDestinatario());
		dadosDestinatario.setInscricaoEstadual(MockUtils.getInscricaoEstadual());
		dadosDestinatario.setTelefone(MockUtils.getTelefone());
		dadosDestinatario.setEndereco(enderecoRemetente);

		/* Nesta POC não estamos considerando estes 2 atores */
		DadosAtorCte dadosExpedidor = null; 
		DadosAtorCte dadosRecebedor = null;

		DadosSeguroCarga dadosSeguroCarga = new DadosSeguroCarga();
		dadosSeguroCarga.setNomeResponsavel(MockUtils.getNomeResponsavelSeguradora());
		dadosSeguroCarga.setNumeroApolice(entrega.getNumeroApoliceSeguroCarga());
		dadosSeguroCarga.setNumeroAverbacao(entrega.getNumeroAverbacaoSeguroCarga());
		
		ComponentesValorPrestacaoServico cvps = new ComponentesValorPrestacaoServico();
		ComponenteValor componenteValor= new ComponenteValor();
		componenteValor.setNome(FRETE_VALOR);
		componenteValor.setValor(entrega.getValorFrete());
		List<ComponenteValor> componentesValores = Arrays.asList(componenteValor);
		cvps.setComponenteValor(componentesValores);
		
		EmissaoCteResponse emissaoCteResponse = new EmissaoCteResponse();
		
		return null;
	}

	private String getCidadeUf(Entrega entrega, int tipoEndereco) {
		return (tipoEndereco == TIPO_ENDERECO_ORIGEM) 
				? entrega.getEnderecoOrigem().getCidade() + " - " 
					+ entrega.getEnderecoOrigem().getUf().getSigla()
				: entrega.getEnderecoDestino().getCidade() + " - " + 
					entrega.getEnderecoDestino().getUf().getSigla();
	}
}
