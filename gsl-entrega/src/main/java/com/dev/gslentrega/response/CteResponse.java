package com.dev.gslentrega.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CteResponse {

	private boolean cteRecebido;
	
	private String mensagem;
	
	private Long protocoloRecebimentoSfc;

}
