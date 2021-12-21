package com.dev.gslentrega.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dev.gslentrega.domain.mother.AndamentoEntregaMother;
import com.dev.gslentrega.response.AndamentoEntregaResponse;
import com.dev.gslentrega.service.EntregaService;

@ActiveProfiles("test")
@WebMvcTest(controllers = EntregaController.class)
public class EntregaControllerUnitTest {
	
	private static final String CONSULTAR_ANDAMENTO_ENTREGA = "/v1/entregas/consultarAndamentoEntrega/";

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private EntregaController entregaController;
	
	@MockBean
	private EntregaService entregaService;
	
	@Test
	void givenValidRequestCodeWhenCallConsultarAndamentoEntregaThenReturnOk() throws Exception {
		
		final AndamentoEntregaResponse response = AndamentoEntregaMother.getAndamentoEntregaResponse();
		
		given(entregaService.consultarAndamentoEntrega(any())).willReturn(response);
		
		mockMvc.perform(
			get(CONSULTAR_ANDAMENTO_ENTREGA + AndamentoEntregaMother.CODIGO_SOLICITACAO))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.codigoSolicitacao", is(response.getCodigoSolicitacao())))
			.andExpect(jsonPath("$.distanciaTotal", is(response.getDistanciaTotal())))
			.andExpect(jsonPath("$.distanciaPercorrida", is(response.getDistanciaPercorrida())))
			.andExpect(jsonPath("$.distanciaApercorrer", is(response.getDistanciaApercorrer())))
			.andExpect(jsonPath("$.percentualPercorrido", is(response.getPercentualPercorrido())))
			.andExpect(jsonPath("$.percentualAPercorrer", is(response.getPercentualAPercorrer())))
			.andExpect(jsonPath("$.status", is(response.getStatus())))
			.andExpect(jsonPath("$.previsaoEntrega", is(response.getPrevisaoEntrega())))
			.andExpect(jsonPath("$.localizacaoCarga.latitude", is(response.getLocalizacaoCarga().getLatitude())))
			.andExpect(jsonPath("$.localizacaoCarga.longitude", is(response.getLocalizacaoCarga().getLongitude())));
	}
}
