package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoModal {
	// Fonte: https://www.supportelogistica.com.br/glossario/o-que-e-modais/
	RODOVIARIO(1, "rodoviário"),
	AEROVIARIO(2, "aeroviário"),
	FERROVIARIO(3, "ferroviário"),
	HIDROVIARIO(4, "hidroviário"),
	DUTOVIARIO(5, "dutoviário"); //o transporte é feito por tubos e dutos.
	
	public int codigo;
	public String descricao;
}
