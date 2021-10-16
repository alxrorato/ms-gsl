package com.dev.gslparceira.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.gslparceira.response.ParceiraResponse;

@Service
public class ParceiraServiceImpl implements ParceiraService {

	private static final Long CNPJ_BOA_ENTREGA = 13814889000192L;
	private static final List<Long> CNPJ_PARCEIROS = Arrays.asList(CNPJ_BOA_ENTREGA, 95260576000102L, 4757984000100L);

	private static final Long CNPJ_TRANSPORTADORA_PARCEIRA_XPTO = 88338539000104L;
	private static final String RAZAO_SOCIAL_PARCEIRA_XPTO = "Transportadora Parceira XPTO Ltda.";
	private static final String NOME_COMERCIAL_PARCEIRA_XPTO = "Transportadora Parceira XPTO.";

	
	@Override
	public ParceiraResponse solicitarParceria(Long cnpjSolicitante) {
		ParceiraResponse response = new ParceiraResponse();
		response.setCnpj(CNPJ_TRANSPORTADORA_PARCEIRA_XPTO);
		response.setRazaoSocial(RAZAO_SOCIAL_PARCEIRA_XPTO);
		response.setNomeComercial(NOME_COMERCIAL_PARCEIRA_XPTO);
		response.setSolicitacaoAceita(CNPJ_PARCEIROS.contains(cnpjSolicitante) ? true : false);
		return response;
	}

}
