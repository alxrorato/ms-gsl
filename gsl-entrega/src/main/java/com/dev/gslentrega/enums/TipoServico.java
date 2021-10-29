package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoServico {

	NORMAL(1, "Normal"),
	REDESPACHHO_INTERMEDIARIO(2, "Redespacho intermediário"),
	VINCULADO_A_MULTIMODAL(3, "Serviço vinculado a multimodal");
	
	public int codigo;
	public String descricao;
}
