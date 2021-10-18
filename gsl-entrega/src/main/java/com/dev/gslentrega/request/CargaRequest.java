package com.dev.gslentrega.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CargaRequest {

	private String natureza;
	private Integer quantidade;
	private String especie;
	private BigDecimal peso;
	private BigDecimal volume;
	private BigDecimal valor;
	private String notaFiscal;

}
