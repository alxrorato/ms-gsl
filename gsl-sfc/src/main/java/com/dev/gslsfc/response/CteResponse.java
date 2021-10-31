package com.dev.gslsfc.response;

import java.io.Serializable;

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
public class CteResponse implements Serializable {

	private static final long serialVersionUID = 591130387924561499L;
	
	private boolean cteRecebido;
	
	private String mensagem;
	
	private Long protocoloRecebimentoSfc;

}
