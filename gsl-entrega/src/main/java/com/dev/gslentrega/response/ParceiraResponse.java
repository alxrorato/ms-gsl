package com.dev.gslentrega.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ParceiraResponse implements Serializable {

	private static final long serialVersionUID = 2640114248986669158L;
	
	private Long cnpj;
	private String razaoSocial;
	private String nomeComercial;
	private boolean solicitacaoAceita;
}