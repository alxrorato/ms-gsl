package com.dev.gslentrega.response;

import java.math.BigDecimal;

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
public class CalculoFreteResponse {
	private BigDecimal fatorCubagemKgPorM3;
	private BigDecimal pesoTotal;
	private BigDecimal volumeTotal;
	private BigDecimal precoPorKg;
	private BigDecimal pesoCubado;
	private BigDecimal valorFrete;
	private String observacao;
}
