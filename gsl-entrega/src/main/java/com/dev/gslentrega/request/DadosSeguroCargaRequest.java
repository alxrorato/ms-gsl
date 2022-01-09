package com.dev.gslentrega.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosSeguroCargaRequest {

	private String nomeSeguradora;
	private String nomeResponsavel;
	private Long numeroApolice;
	private Long numeroAverbacao;
}
