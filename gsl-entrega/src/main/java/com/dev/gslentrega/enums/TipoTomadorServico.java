package com.dev.gslentrega.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTomadorServico {
	/* Tomador do serviço é quem paga o frete da operação do transporte, podendo ser o 
	 * remetente, destinatário, recebedor ou outra empresa.
	 */
	REMETENTE(1, "Remetente"),
	DESTINATARIO(2, "Destinatário"),
	RECEBEDOR(3, "Recebedor"),
	OUTRA_EMPRESA(3, "Outra Empresa");
	
	public int codigo;
	public String descricao;
	
}
