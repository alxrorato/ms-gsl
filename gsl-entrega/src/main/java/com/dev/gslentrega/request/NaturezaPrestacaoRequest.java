package com.dev.gslentrega.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class NaturezaPrestacaoRequest {

	private String codigoNomeNaturezaPrestacao;
	private String localInicioPrestacao; //"cidade - UF"
	private String localTerminoPrestacao; //"cidade - UF"

}
