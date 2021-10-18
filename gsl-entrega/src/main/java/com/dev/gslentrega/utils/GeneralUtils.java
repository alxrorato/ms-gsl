package com.dev.gslentrega.utils;

import java.math.BigDecimal;

public class GeneralUtils {
	
	public static final BigDecimal ZERO = new BigDecimal(0);
	public static final BigDecimal CEM = new BigDecimal(100);
	public static final BigDecimal DUZENTOS = new BigDecimal(200);
	public static final BigDecimal QUATROCENTOS = new BigDecimal(400);
	public static final BigDecimal SEISCENTOS = new BigDecimal(600);
	public static final BigDecimal OITOCENTOS = new BigDecimal(800);

	public static BigDecimal percentual(BigDecimal valorTotal, BigDecimal valorBase) {
		return valorBase.multiply(CEM).divide(valorTotal);
	}

}
