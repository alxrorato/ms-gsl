package com.dev.gslentrega.response;

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
public class DadosSeguroCarga {

	private String nomeResponsavel;
	private Long numeroApolice;
	private Long numeroAverbacao;
}
