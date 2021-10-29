package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCte {

	// Fonte: https://gestran.com.br/2018/08/saiba-o-que-e-cte/
	NORMAL(1, "Normal"),
	COMPLEMENTAR(2, "CT-e complementar"),
	SUBSTITUICAO_ANULACAO(3, "CTe de Substituição e Anulação");
	
	public int codigo;
	public String descricao;
	
}
