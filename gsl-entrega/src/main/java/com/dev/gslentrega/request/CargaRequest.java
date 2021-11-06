package com.dev.gslentrega.request;

import java.math.BigDecimal;

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
@ApiModel(description = "Dados da carga")
public class CargaRequest {

	@ApiModelProperty(name = "natureza", value = "Natureza da carga", required = true, example = "granel")
	private String natureza;
	
	@ApiModelProperty(name = "quantidade", value = "Quantidade de componentes da carga", required = true, example = "5")
	private Integer quantidade;

	@ApiModelProperty(name = "especie", value = "Espécie ou tipo dos componentes da carga", required = true, example = "alimentos")
	private String especie;
	
	@ApiModelProperty(name = "peso", value = "Peso da carga (em Kg)", required = true, example = "3")
	private BigDecimal peso;
	
	@ApiModelProperty(name = "volume", value = "Volume da carga (em m3)", required = true, example = "2")
	private BigDecimal volume;
	
	@ApiModelProperty(name = "valor", value = "Valor da carga", required = true, example = "57.0")
	private BigDecimal valor;
	
	@ApiModelProperty(name = "notaFiscal", value = "Nota Fiscal da carga", required = true, example = "NF0005")
	private String notaFiscal;

}
