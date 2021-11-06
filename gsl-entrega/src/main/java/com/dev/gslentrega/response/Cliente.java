package com.dev.gslentrega.response;

import java.time.LocalDateTime;

import com.dev.gslentrega.enums.StatusCliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Resposta da consulta a um cliente")
public class Cliente {

	@ApiModelProperty(name = "id", value = "Identificador do registro do cliente no banco de dados", example = "1")
	private Long id;
	
	@ApiModelProperty(name = "cnpj", value = "CNPJ do cliente", example = "80200396000150")
	private Long cnpj;
	
	@ApiModelProperty(name = "razaoSocial", value = "Razão social do cliente", example = "Loja de Celulares Ltda.")
	private String razaoSocial;

	@ApiModelProperty(name = "nomeComercial", value = "Nome comercial do cliente", example = "Loja de Celulares")
	private String nomeComercial;
	
	@ApiModelProperty(name = "email", value = "E-mail do cliente", example = "lojacel@gmail.com")
	private String email;
	
	@ApiModelProperty(name = "telefone", value = "Telefone do cliente", example = "(41)97777-1111")
	private String telefone;
	
	@ApiModelProperty(name = "inscricaoEstadual", value = "Inscrição estadual do cliente", example = "625.768.399.392")
	private String inscricaoEstadual;
	
	@ApiModelProperty(name = "status", value = "Situação do cliente na base de dados (A - Ativo; I - Inativo)", 
			example = "A")
	private StatusCliente status;

	@ApiModelProperty(name = "dataInclusao", value = "Data/hora da inclusão do cliente no banco de dados", 
			example = "2021-09-05T00:00:00")
	private LocalDateTime dataInclusao;

	@ApiModelProperty(name = "dataExclusao", value = "Data/hora da exclusão lógica (inativação) do cliente "
			+ "no banco de dados", example = "2021-09-25T00:00:00")
	private LocalDateTime dataExclusao;
	
	@ApiModelProperty(name = "endereco", value = "Endereço do cliente")
	private Endereco endereco;
}
