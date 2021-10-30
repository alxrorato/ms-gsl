package com.dev.gslparceira.response;

import com.dev.gslparceira.enums.UF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoParceiraResponse {

	private String logradouro;
	private String bairro;
	private String cidade;
	private UF uf;
	private String cep;
	private String numero;
	private String complemento;

}
