package com.dev.gslentrega.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CteResponse {

	private boolean cteRecebido;
	
	private String mensagem;
	
	private Long protocoloRecebimentoSfc;

}
