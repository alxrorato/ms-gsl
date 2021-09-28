package com.dev.gslcliente.enums;

import java.util.Arrays;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCliente {

	A("A", "Ativo"), 
	I("I", "Inativo");
	
	private String codigo;
	private String descricao;
	
	public static String getCodigoByDescricao(String descricao) {
		Optional<StatusCliente> statusCliente = 
				Arrays.stream(StatusCliente.values())
				.filter(item -> item.getDescricao().equalsIgnoreCase(descricao))
				.findFirst();
		
		return statusCliente.map(StatusCliente::getCodigo).orElse("");
	}

	public static String getDescricaoByCodigo(String codigo) {
		Optional<StatusCliente> statusCliente = 
				Arrays.stream(StatusCliente.values())
				.filter(item -> item.getCodigo().equalsIgnoreCase(codigo))
				.findFirst();
		
		return statusCliente.map(StatusCliente::getDescricao).orElse("");
	}	
}
