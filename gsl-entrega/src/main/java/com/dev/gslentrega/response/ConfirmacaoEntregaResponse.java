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
@ApiModel(description = "Resposta da transação referente a entrega da mercadoria ao cliente final")
public class ConfirmacaoEntregaResponse implements Serializable {

	private static final long serialVersionUID = 12807853540592816L;

	@ApiModelProperty(name = "codigoSolicitacao", value = "Código de solicitação para pesquisa da entrega no sistema", 
			example = "116922963214")
	private Long codigoSolicitacao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(name = "dataFinalizacao", value = "Data/hora da realização da entrega", example = "06-11-2021 05:52:57")
	private LocalDateTime dataFinalizacao;
	
	@ApiModelProperty(name = "mensagem", value = "Mensagem de finalização da entrega", example = "Mercadoria entregue ao cliente final.")
	private String mensagem;
	
	@ApiModelProperty(name = "recebedorEntrega", value = "Nome de quem recebeu a mercadoria/portaria do condomínio/outros", 
			example = "Felipe Carvalho Gonçalves")
	private String recebedorEntrega;
}
