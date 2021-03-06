package com.dev.gslparceira.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Getter;

import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum UF {

	AC("AC", "Acre"), 
	AL("AL", "Alagoas"),
	AM("AM", "Amazonas"),
	BA("BA", "Bahia"),
	CE("CE", "Ceará"),
	DF("DF", "Distrito Federal"),
	ES("ES", "Espírito Santo"),
	GO("GO", "Goiás"),
	MA("MA", "Maranhão"),
	MT("MT", "Mato Grosso"),
	MS("MS", "Mato Grosso do Sul"),
	MG("MG", "Minas Gerais"),
	PA("PA", "Pará"),
	PB("PB", "Paraíba"),
	PR("PR", "Paraná"),
	PE("PE", "Pernambuco"),
	PI("PI", "Piauí"),
	RJ("RJ", "Rio de Janeiro"),
	RN("RN", "Rio Grande do Norte"),
	RS("RS", "Rio Grande do Sul"),
	RO("RO", "Rondônia"),
	RR("RR", "Roraima"),
	SC("SC", "Santa Catarina"),
	SP("SP", "São Paulo"),
	SE("SE", "Sergipe"),
	TO("TO", "Tocantins");
	
	private String sigla;
	private String nome;

	private static final Map<String, UF> ufBySigla = new HashMap<>();
	
	static {
		for (UF uf : UF.values()) {
			ufBySigla.put(uf.getSigla(), uf);
		}
	}
	
	public static UF getUFBySigla(String sigla) {
		return ufBySigla.get(sigla);
	}
	
	public static String getSiglaByNomeDescricao(String nome) {
		Optional<UF> uf = 
				Arrays.stream(UF.values())
				.filter(item -> item.getNome().equalsIgnoreCase(nome))
				.findFirst();
		
		return uf.map(UF::getSigla).orElse("");
	}

	public static String getNomeBySigla(String sigla) {
		Optional<UF> uf = 
				Arrays.stream(UF.values())
				.filter(item -> item.getSigla().equalsIgnoreCase(sigla))
				.findFirst();
		
		return uf.map(UF::getNome).orElse("");
	}	
	
}
