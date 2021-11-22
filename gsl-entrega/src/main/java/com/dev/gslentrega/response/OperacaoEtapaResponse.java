package com.dev.gslentrega.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.dev.gslentrega.enums.StatusEntrega;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "Resposta da transação referente a uma operação referente a uma das etapas do processo de entrega")
public class OperacaoEtapaResponse implements Serializable {


	private static final long serialVersionUID = 9051027870437338226L;
	
	@ApiModelProperty(name = "codigoSolicitacao", value = "Código de solicitação para pesquisa da entrega no sistema", 
			example = "116922963214")
	private Long codigoSolicitacao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@ApiModelProperty(name = "dataOperacao", value = "Data/hora da realização da operação",	example = "06-11-2021 05:52:57")
	private LocalDateTime dataOperacao;
	
	@ApiModelProperty(name = "mensagem", value = "Mensagem referente a operação efetuada", 
			example = "Operação efetuada")
	private String mensagem;
}
