package com.dev.gslsfc.request;

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
public class NaturezaPrestacaoRequest {

	private String codigoNomeNaturezaPrestacao;
	private String localInicioPrestacao; //"cidade - UF"
	private String localTerminoPrestacao; //"cidade - UF"

}
