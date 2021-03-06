package com.dev.gslsfc.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DadosSeguroCargaRequest {

	private String nomeResponsavel;
	private Long numeroApolice;
	private Long numeroAverbacao;
}
