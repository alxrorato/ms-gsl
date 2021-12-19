package com.dev.gslentrega.response;

import java.math.BigDecimal;

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
@ApiModel(description = "Resposta da solicitação de uma estimativa de cálculo do valor do frete")
public class CalculoFreteResponse {
	
	@ApiModelProperty(name = "fatorCubagemKgPorM3", value = "Fator (Kg/m3) utilizado no cálculo do peso cubado da carga", 
			example = "300")
	protected BigDecimal fatorCubagemKgPorM3;
	
	@ApiModelProperty(name = "pesoTotal", value = "Peso total (em Kg) da carga a ser transportada",	example = "100")
	protected BigDecimal pesoTotal;
	
	@ApiModelProperty(name = "volumeTotal", value = "Volume total (em m3) da carga a ser transportada",	example = "15")
	protected BigDecimal volumeTotal;
	
	@ApiModelProperty(name = "precoPorKg", value = "Preço de cada Kg a ser considerado para o cálculo "
			+ "do valor do frete",	example = "1.5")
	protected BigDecimal precoPorKg;
	
	@ApiModelProperty(name = "pesoCubado", value = "Volume (m3) transformado em peso (Kg) através do fator de cubagem (Kg/m3)."
			+ "Será considerado no cálculo do frete se ele for maior que o peso total. Nesta situação, significa que trata-se"
			+ "de uma carga mais leve, mas que tem grande volume, o que lotaria mais rapidamente o caminhão, por exemplo",
			example = "4500")
	protected BigDecimal pesoCubado;
	
	@ApiModelProperty(name = "valorFrete", value = "Valor estimado do frete, calculado multiplicando-se o preço por Kg pelo maior "
			+ "peso entre o peso total e o peso cubado ", example = "6750")
	protected BigDecimal valorFrete;
	
	@ApiModelProperty(name = "observacao", value = "Observação referente ao cálculo da estimativa do valor do frete",
			example = "No cálculo foi considerado o peso cubado da carga")
	protected String observacao;
}
