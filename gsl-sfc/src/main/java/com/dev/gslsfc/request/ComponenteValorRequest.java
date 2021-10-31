package com.dev.gslsfc.request;

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
public class ComponenteValorRequest {
	private String nome; //Ex.: "Frete Valor"
	private BigDecimal valor;
}
