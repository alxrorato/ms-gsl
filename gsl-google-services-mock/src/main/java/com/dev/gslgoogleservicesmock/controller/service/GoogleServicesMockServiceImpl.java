package com.dev.gslgoogleservicesmock.controller.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.dev.gslgoogleservicesmock.response.LocalizacaoGoogleResponse;

@Service
public class GoogleServicesMockServiceImpl implements GoogleServicesMockService {

	private static final BigDecimal ZERO = new BigDecimal(0);
	private static final BigDecimal UM = new BigDecimal(1);
	private static final int SCALE_4 = 4;
	private static final BigDecimal LIMITE_LATITUDE_LONGITUDE = new BigDecimal(99);

	@Override
	public LocalizacaoGoogleResponse obterLocalizacao() {
		return new LocalizacaoGoogleResponse(getLatitudeLongitude(LIMITE_LATITUDE_LONGITUDE),
				getLatitudeLongitude(LIMITE_LATITUDE_LONGITUDE));
	}

	private BigDecimal getLatitudeLongitude(BigDecimal limit) {
		BigDecimal latitudeLongitude = ZERO;
		do {
			latitudeLongitude = getRandomNumber(UM, limit, SCALE_4);
		} while (latitudeLongitude.compareTo(ZERO) == 0);
		return latitudeLongitude;
	}
	
    private static BigDecimal getRandomNumber(BigDecimal min, BigDecimal max, int scale) {
    	
 	   return min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)))
 			   .setScale(scale, RoundingMode.HALF_UP);
  }
	
}
