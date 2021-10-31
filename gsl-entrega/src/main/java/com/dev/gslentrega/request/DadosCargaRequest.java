package com.dev.gslentrega.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class DadosCargaRequest {

	private String produtoPredominante; //combustivel
	
	private String outrasCaracteristicasCarga; //granel
	
	private BigDecimal valorTotalMercadoria; // soma dos valores de cada carga
	
	private BigDecimal pesoBruto;
	private BigDecimal pesoAferido;
	private BigDecimal cubagemM3; //pesoCubado
	private BigDecimal volume;

}
