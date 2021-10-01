package com.dev.gslentrega.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
	
	private static final Map<String, StatusCliente> statusByCodigo = new HashMap<>();
	
	static {
		for (StatusCliente status : StatusCliente.values()) {
			statusByCodigo.put(status.getCodigo(), status);
		}
	}
	
	public static StatusCliente getStatusByCodigo(String codigo) {
		return statusByCodigo.get(codigo);
	}
	
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
