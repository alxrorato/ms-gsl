package com.dev.gslentrega.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculoFreteRequest {
	private String cepOrigem;
	private String cepDestino;
	private List<CargaRequest> cargas;
}
