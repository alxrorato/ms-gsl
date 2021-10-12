package com.dev.gslentrega.utils;

import java.util.Arrays;
import java.util.List;

public class UFUtils {

	public static final String NORTE = "NORTE";
	public static final String NORDESTE = "NORDESTE";
	public static final String CENTRO_OESTE = "CENTRO OESTE";
	public static final String SUDESTE = "SUDESTE";
	public static final String SUL = "SUL";
	
	public static final List<String> REGIAO_NORTE = Arrays.asList("AC", "AM", "PA", "RO", "RR");
	public static final List<String> REGIAO_NORDESTE = Arrays.asList("AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE");
	public static final List<String> REGIAO_CENTRO_OESTE = Arrays.asList("DF", "GO", "MT", "MS", "TO");
	public static final List<String> REGIAO_SUDESTE = Arrays.asList("ES", "MG", "RJ", "SP");
	public static final List<String> REGIAO_SUL = Arrays.asList("PR", "SC", "RS");
	
	public String getRegiaoByUf(String uf) {
		if (REGIAO_NORTE.contains(uf)) {
			return NORTE;
		} else if (REGIAO_NORDESTE.contains(uf)) {
			return NORDESTE;
		} else if (REGIAO_CENTRO_OESTE.contains(uf)) {
			return CENTRO_OESTE;
		} else if (REGIAO_SUDESTE.contains(uf)) {
			return SUDESTE;
		} else {
			return SUL;
		}
	}
}
