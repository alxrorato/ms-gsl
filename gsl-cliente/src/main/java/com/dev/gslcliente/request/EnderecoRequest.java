package com.dev.gslcliente.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequest {
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private Integer numero;
	private String complemento;

}