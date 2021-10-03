package com.dev.gslentrega.request;

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
	private Double peso;
	private Double volume;
	private Double valor;
	private String notaFiscal;

}
