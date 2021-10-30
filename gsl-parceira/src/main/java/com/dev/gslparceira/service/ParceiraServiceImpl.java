package com.dev.gslparceira.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.gslparceira.enums.UF;
import com.dev.gslparceira.response.EnderecoParceira;
import com.dev.gslparceira.response.ParceiraResponse;

@Service
public class ParceiraServiceImpl implements ParceiraService {

	private static final Long CNPJ_BOA_ENTREGA = 13814889000192L;
	private static final List<Long> CNPJ_PARCEIROS = Arrays.asList(CNPJ_BOA_ENTREGA, 95260576000102L, 4757984000100L);

	private static final Long CNPJ_TRANSPORTADORA_PARCEIRA = 32614634000120L;
	private static final String RAZAO_SOCIAL_PARCEIRA = "Transportadora Entrega Rápida Ltda.";
	private static final String NOME_COMERCIAL_PARCEIRA = "Transportadora Entrega Rápida.";

	private static final String INSCRICAO_ESTADUAL_PARCEIRA = "67.375.056-6";
	private static final String TELEFONE_PARCEIRA = "(11)5773-8532";
	private static final String LOGRADOURO_PARCEIRA = "Rua Paraíso do Norte";
	private static final String NUMERO_PARCEIRA = "817";
	private static final String COMPLEMENTO_PARCEIRA = "Galpão";
	private static final String BAIRRO_PARCEIRA = "COROADO";
	private static final String CIDADE_PARCEIRA = "MANAUS";
	private static final UF UF_PARCEIRA = UF.getUFBySigla("AM");
	private static final String CEP_PARCEIRA = "69080-350";
	
	
	@Override
	public ParceiraResponse solicitarParceria(Long cnpjSolicitante) {
		ParceiraResponse response = new ParceiraResponse();
		response.setCnpj(CNPJ_TRANSPORTADORA_PARCEIRA);
		response.setRazaoSocial(RAZAO_SOCIAL_PARCEIRA);
		response.setNomeComercial(NOME_COMERCIAL_PARCEIRA);
		response.setInscricaoEstadual(INSCRICAO_ESTADUAL_PARCEIRA);
		response.setTelefone(TELEFONE_PARCEIRA);
		
		EnderecoParceira enderecoParceira = new EnderecoParceira();
		enderecoParceira.setLogradouro(LOGRADOURO_PARCEIRA);
		enderecoParceira.setNumero(NUMERO_PARCEIRA);
		enderecoParceira.setComplemento(COMPLEMENTO_PARCEIRA);
		enderecoParceira.setBairro(BAIRRO_PARCEIRA);
		enderecoParceira.setCidade(CIDADE_PARCEIRA);
		enderecoParceira.setUf(UF_PARCEIRA);
		enderecoParceira.setCep(CEP_PARCEIRA);
		
		response.setEnderecoParceira(enderecoParceira);
		response.setSolicitacaoAceita(CNPJ_PARCEIROS.contains(cnpjSolicitante) ? true : false);
		return response;
	}

}
