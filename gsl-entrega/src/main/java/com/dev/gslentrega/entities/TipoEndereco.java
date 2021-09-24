package com.dev.gslentrega.entities;

public enum TipoEndereco {

	ORIGEM_ENTREGA("O", "Origem"), 
	FINAL_ENTREGA("F", "Final"),
	CLIENTE("C", "Cliente"),
	FORNECEDOR("F", "Fornecedor"),
	COLABORADOR("L", "Colaborador");
	
	private String codigo;
	private String descricao;
	
	TipoEndereco(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}	
}
