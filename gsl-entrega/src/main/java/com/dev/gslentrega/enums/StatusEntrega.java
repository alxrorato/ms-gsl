package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEntrega {
	SOLICITACAO(1, "entrega em fase de solicitação"),
	COLETA(2, "entrega em coleta"),
	ROTEIRIZACAO(3, "entrega em roteirização"),
	TRANSPORTE(4, "entrega em transporte"),
	DISTRIBUICAO(5, "entrega em distribuição no CD"),
	LAST_MILE(6, "entrega em last-mile (a caminho do cliente final)"),
	FINALIZADA(7, "entrega finalizada"),
	CANCELADA(8, "entrega cancelada");
	
	public int codigo;
	public String descricao;
}
