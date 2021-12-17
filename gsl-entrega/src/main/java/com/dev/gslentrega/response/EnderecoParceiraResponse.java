package com.dev.gslentrega.response;

import com.dev.gslentrega.enums.UF;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnderecoParceiraResponse {

	private String logradouro;
	private String bairro;
	private String cidade;
	private UF uf;
	private String cep;
	private String numero;
	private String complemento;

}
