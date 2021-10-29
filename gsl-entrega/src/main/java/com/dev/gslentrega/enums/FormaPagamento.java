package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaPagamento {

	A_PAGAR(1, "A pagar"),
	BOLETO(2, "Boleto");
	
	public int codigo;
	public String descricao;
	
}
