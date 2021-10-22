package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEntrega {

	ANALISE("entrega em análise"),
	COLETA("entrega em coleta"),
	ROTEIRIZACAO("entrega em roteirização"),
	TRANSPORTE("entrega em transporte"),
	DISTRIBUICAO("entrega em distribuição"),
	FINALIZADA("entrega finalizada");
	
	public String descricao;
}
