package com.dev.gslentrega.response;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dev.gslentrega.enums.UF;

public class EnderecoClienteResponse {

	private Long id;	
	private String logradouro;
	private String bairro;
	private String cidade;
	private UF uf;
	private String cep;
	private Integer numero;
	private String complemento;
	
}
