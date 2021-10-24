package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEntrega {
	SOLICITACAO("entrega em fase de solicitação"),
	COLETA("entrega em coleta"),
	ROTEIRIZACAO("entrega em roteirização"),
	TRANSPORTE("entrega em transporte"),
	DISTRIBUICAO("entrega em distribuição no CD"),
	LAST_MILE("entrega em last-mile (a caminho do cliente final)"),
	FINALIZADA("entrega finalizada"),
	CANCELADA("entrega cancelada");
	
	public String descricao;
}
