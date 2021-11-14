package com.dev.gslcliente.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.dev.gslcliente.enums.StatusCliente;

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
@Entity
@Table(name = "tb_cliente", uniqueConstraints = 
	@UniqueConstraint(columnNames = "cnpj", name = "cnpj_uk"))
@ApiModel(description = "Resposta do cadastro de um cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@ApiModelProperty(name = "id", value = "Identificador do registro do cliente no banco de dados", example = "1")
	private Long id;
	
	@NotNull
	@ApiModelProperty(name = "cnpj", value = "CNPJ do cliente", example = "99825101000169")
	private Long cnpj;
	
	@NotNull
	@ApiModelProperty(name = "razaoSocial", value = "Razão social do cliente", example = "Comercio de Artigos Esportivos Ltda")
	private String razaoSocial;
	
	@ApiModelProperty(name = "nomeComercial", value = "Nome comercial do cliente", example = "Comercio de Artigos Esportivos")
	private String nomeComercial;
	
	@ApiModelProperty(name = "email", value = "E-mail do cliente", example = "artigos.esportivos@gmail.com")
	private String email;
	
	@ApiModelProperty(name = "telefone", value = "Telefone do cliente", example = "(48)98888-7777")
	private String telefone;
	
	@ApiModelProperty(name = "inscricaoEstadual", value = "Número da inscrição estadual do cliente", example = "964.869.414.930")
	private String inscricaoEstadual;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(name = "status", value = "Situação do cliente na base de dados (A: ativo; I: inativo)", example = "A")
	private StatusCliente status;
	
	@NotNull
	@ApiModelProperty(name = "dataInclusao", value = "Data/hora da inclusão do cliente no banco de dados", example = "2021-09-05T00:00:00")
	private LocalDateTime dataInclusao;

	@ApiModelProperty(name = "dataExclusao", value = "Data/hora da inativação do cliente no banco de dados", example = "2021-09-25T00:00:00")
	private LocalDateTime dataExclusao;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
}
