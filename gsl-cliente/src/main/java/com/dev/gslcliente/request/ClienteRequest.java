package com.dev.gslcliente.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

	private Long cnpj;
	private String razaoSocial;
	private String nomeComercial;
	private String email;
	private String telefone;
	private EnderecoRequest endereco;
	
}
