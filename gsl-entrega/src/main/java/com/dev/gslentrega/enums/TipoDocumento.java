package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {
	CPF(1),
	CNPJ(2);
	
	public int tipoDocumento;
}
