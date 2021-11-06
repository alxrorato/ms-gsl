package com.dev.gslentrega.request;

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
@ApiModel(description = "Requisição para atualização de uma entrega")
public class SolicitacaoRequest {

	@ApiModelProperty(name = "codigoSolicitacao", 
			value = "Código de solicitação gerado para o cliente após a conclusão da solicitação de "
					+ "uma entrega e que serve para rastreio da entrega",
			example = "116922963214")
	private Long codigoSolicitacao;
}
