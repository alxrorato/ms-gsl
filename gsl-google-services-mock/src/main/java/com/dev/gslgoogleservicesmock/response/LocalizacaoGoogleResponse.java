package com.dev.gslgoogleservicesmock.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalizacaoGoogleResponse {
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;

}
