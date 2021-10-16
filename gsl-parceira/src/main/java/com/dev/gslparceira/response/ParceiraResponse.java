package com.dev.gslparceira.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParceiraResponse {

	private Long cnpj;
	private String razaoSocial;
	private String nomeComercial;
	private boolean solicitacaoAceita;
}
