package com.dev.gslentrega.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.dev.gslentrega.enums.StatusEntrega;

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
@ApiModel(description = "Resposta da pesquisa do andamento da entrega")
public class AndamentoEntregaResponse implements Serializable {

	private static final long serialVersionUID = 600305478199596589L;

	@ApiModelProperty(name = "codigoSolicitacao", 
			value = "Código de solicitação gerado para o cliente após a conclusão da solicitação de uma entrega e que "
					+ "serve para rastreio da entrega",	example = "116922963214")
	private Long codigoSolicitacao;
	
	@ApiModelProperty(name = "distanciaTotal", value = "Distância (em Km) entre o enderelo de origem e o de destino", 
			example = "67.41")
	private BigDecimal distanciaTotal;
	
	@ApiModelProperty(name = "distanciaPercorrida", value = "Distância (em Km) percorrida até o momento", 
			example = "16.00")
	private BigDecimal distanciaPercorrida;
	
	@ApiModelProperty(name = "distanciaApercorrer", value = "Distância (em Km) que falta até o destino final", 
			example = "51.41")
	private BigDecimal distanciaApercorrer;
	
	@ApiModelProperty(name = "percentualPercorrido", value = "Percentual (%) do trajeto já percorrido", 
			example = "23.78")
	private BigDecimal percentualPercorrido;
	
	@ApiModelProperty(name = "percentualAPercorrer", value = "Percentual (%) do trajeto que falta percorrer", 
			example = "76.22")
	private BigDecimal percentualAPercorrer;
	
	@ApiModelProperty(name = "status", value = "Situação do andamento da entrega", example = "entrega em transporte")
	private String status;
	
	@ApiModelProperty(name = "previsaoEntrega", value = "Tempo que falta para a entrega ser finalizada", 
			example = "Em 3 dias")
	private String previsaoEntrega;
	
	@ApiModelProperty(name = "localizacaoCarga", value = "Localização atual da carga em latitude e longitude")	
	private LocalizacaoCarga localizacaoCarga;
}
