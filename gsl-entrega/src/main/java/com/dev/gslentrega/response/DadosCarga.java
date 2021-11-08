package com.dev.gslentrega.response;

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
@ApiModel(description = "Dados gerais da carga a ser transportada")
public class DadosCarga {

	@ApiModelProperty(name = "produtoPredominante", value = "Produto predominante na carga", example = "frutas")
	private String produtoPredominante;
	
	@ApiModelProperty(name = "outrasCaracteristicasCarga", value = "Outras características da carga (alguma natureza presente)", 
			example = "granel")
	private String outrasCaracteristicasCarga;
	
	@ApiModelProperty(name = "valorTotalMercadoria", value = "Valor total das mercadorias a serem transportadas",	example = "6750.00")
	private BigDecimal valorTotalMercadoria;
	
	@ApiModelProperty(name = "pesoBruto", value = "Peso total (em Kg) da carga a ser transportada", example = "100.00")
	private BigDecimal pesoBruto;
	
	@ApiModelProperty(name = "pesoAferido", value = "Maior peso (em Kg) entre o peso bruto e o peso cubado", example = "4500.00")
	private BigDecimal pesoAferido; 
	
	@ApiModelProperty(name = "cubagemM3", value = "Fator de cubagem (em Kg/m3) utilizada no cálculo do frete", example = "300.00")
	private BigDecimal cubagemM3; 
	
	@ApiModelProperty(name = "volume", value = "Volume (em m3) da carga a ser transportada", example = "15.00")
	private BigDecimal volume;

}
