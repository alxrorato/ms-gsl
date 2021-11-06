package com.dev.gslentrega.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@ApiModel(description = "Resposta da transação de pagamento do serviço de transporte referente a entrega")
public class PagamentoResponse implements Serializable {

	private static final long serialVersionUID = 1516689618136550305L;

	@ApiModelProperty(name = "codigoSolicitacao", value = "Código de solicitação para pesquisa da entrega no sistema", 
			example = "116922963214")
	private Long codigoSolicitacaoEntrega;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(name = "dataPagamento", value = "Data/hora do pagamento à transportadora Boa Entrega", 
		example = "06-11-2021 05:02:22")
	private LocalDateTime dataPagamento;
	
	@ApiModelProperty(name = "valorPago", value = "Valor pago à transportadora Boa Entrega pelo serviço", 
			example = "4500.00")
	private BigDecimal valorPago;
	
	@ApiModelProperty(name = "mensagem", value = "Mensagem após conclusão do pagamento", 
			example = "Pagamento efetuado com sucesso")
	private String mensagem;
}
