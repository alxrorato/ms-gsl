package com.dev.gslentrega.response;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocalizacaoGoogleResponse implements Serializable {
	
	private static final long serialVersionUID = 2925954690294315089L;

	private BigDecimal latitude;
	
	private BigDecimal longitude;

}
