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
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "Resposta da solicitação de uma estimativa de cálculo do valor do frete")
public class CalculoFreteResponseV2 extends CalculoFreteResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@ApiModelProperty(name = "dataPrevisaoEntrega", value = "Data prevista para entrega", example = "18-12-2021")
	protected LocalDateTime dataPrevisaoEntrega;

}
