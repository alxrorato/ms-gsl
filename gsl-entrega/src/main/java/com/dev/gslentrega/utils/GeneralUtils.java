package com.dev.gslentrega.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeneralUtils {
	
	public static final BigDecimal ZERO = new BigDecimal(0);
	public static final BigDecimal UM = new BigDecimal(1);
	public static final BigDecimal CEM = new BigDecimal(100);
	public static final BigDecimal DUZENTOS = new BigDecimal(200);
	public static final BigDecimal QUATROCENTOS = new BigDecimal(400);
	public static final BigDecimal SEISCENTOS = new BigDecimal(600);
	public static final BigDecimal OITOCENTOS = new BigDecimal(800);

	public static BigDecimal percentual(BigDecimal valorTotal, BigDecimal valorBase) {
		return valorBase.multiply(CEM).divide(valorTotal, 2, RoundingMode.HALF_UP);
	}

}
