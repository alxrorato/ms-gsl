package com.dev.gslentrega.domain.mother;

import java.math.BigDecimal;

import com.dev.gslentrega.response.AndamentoEntregaResponse;
import com.dev.gslentrega.response.LocalizacaoCarga;

public class AndamentoEntregaMother {

	private AndamentoEntregaMother() {
		
	}

	public static final Long CODIGO_SOLICITACAO = 884815120936L;
	private static final BigDecimal DISTANCIA_TOTAL = new BigDecimal(422.51);
	private static final BigDecimal DISTANCIA_PERCORRIDA = new BigDecimal(139.54);
	private static final BigDecimal DISTANCIA_A_PERCORRER = new BigDecimal(282.97);
	private static final BigDecimal PERCENTUAL_PERCORRIDO = new BigDecimal(33.03);
	private static final BigDecimal PERCENTUAL_A_PERCORRER = new BigDecimal(66.97);
	private static final String STATUS_ANDAMENTO_ENTREGA = "entrega em transporte";
	private static final String PREVISAO_ENTREGA = "Em 3 dias";
	private static final BigDecimal LATITUDE = new BigDecimal(79.7350);
	private static final BigDecimal LONGITUDE = new BigDecimal(46.0156);
	
	public static AndamentoEntregaResponse getAndamentoEntregaResponse() {
		
		LocalizacaoCarga localizacaoCarga = new LocalizacaoCarga();
		localizacaoCarga.setLatitude(LATITUDE);
		localizacaoCarga.setLongitude(LONGITUDE);

		AndamentoEntregaResponse response = new AndamentoEntregaResponse();
		response.setCodigoSolicitacao(CODIGO_SOLICITACAO);
		response.setDistanciaTotal(DISTANCIA_TOTAL);
		response.setDistanciaPercorrida(DISTANCIA_PERCORRIDA);
		response.setDistanciaApercorrer(DISTANCIA_A_PERCORRER);
		response.setPercentualPercorrido(PERCENTUAL_PERCORRIDO);
		response.setPercentualAPercorrer(PERCENTUAL_A_PERCORRER);
		response.setStatus(STATUS_ANDAMENTO_ENTREGA);
		response.setPrevisaoEntrega(PREVISAO_ENTREGA);
		response.setLocalizacaoCarga(localizacaoCarga);
		
		return response;
	}
}
