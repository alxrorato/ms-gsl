package com.dev.gslcliente.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Dados para cadastro de um cliente")
public class ClienteRequest {

	@ApiModelProperty(name = "cnpj", value = "CNPJ do cliente", required = true, example = "99825101000169")
	private Long cnpj;

	@ApiModelProperty(name = "razaoSocial", value = "Razão social do cliente", required = true, example = "Comercio de Artigos Esportivos Ltda")
	private String razaoSocial;
	
	@ApiModelProperty(name = "nomeComercial", value = "Nome comercial do cliente", example = "Comercio de Artigos Esportivos")
	private String nomeComercial;

	@ApiModelProperty(name = "email", value = "E-mail do cliente", example = "artigos.esportivos@gmail.com")
	private String email;
	
	@ApiModelProperty(name = "inscricaoEstadual", value = "Número da inscrição estadual do cliente", required = true, example = "964.869.414.930")
	private String inscricaoEstadual;
	
	@ApiModelProperty(name = "telefone", value = "Telefone do cliente", required = true, example = "(48)98888-7777")
	private String telefone;
	
	@ApiModelProperty(name = "endereco", value = "Endereço do cliente")
	private EnderecoRequest endereco;
	
}
