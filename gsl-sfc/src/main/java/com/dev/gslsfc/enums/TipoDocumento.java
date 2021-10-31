package com.dev.gslsfc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {
	CPF(1, "CPF"),
	CNPJ(2, "CNPJ");
	
	public int codigo;
	public String descricao;
}
