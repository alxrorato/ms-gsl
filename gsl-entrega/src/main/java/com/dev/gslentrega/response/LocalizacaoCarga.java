package com.dev.gslentrega.response;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LocalizacaoCarga implements Serializable {

	private static final long serialVersionUID = 6823676838248465565L;
	
	private BigDecimal latitude;
	private BigDecimal longitude;
}
