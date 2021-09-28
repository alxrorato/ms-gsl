package com.dev.gslcliente.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

	private Long cnpj;
	private String razaoSocial;
	private String nomeComercial;
	private String email;
	private String telefone;
	private String status;
	private Date dataInclusao;
	private Date dataExclusao;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private Integer numero;
	private String complemento;
	
}
