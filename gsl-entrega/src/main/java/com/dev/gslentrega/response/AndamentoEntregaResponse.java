package com.dev.gslentrega.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.dev.gslentrega.enums.StatusEntrega;

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
public class AndamentoEntregaResponse {

	private Long codigoSolicitacao;
	private BigDecimal distanciaTotal;
	private BigDecimal distanciaPercorrida;
	private BigDecimal distanciaApercorrer;
	private BigDecimal percentualPercorrido;
	private BigDecimal percentualAPercorrer;
	private String previsaoEntrega;
}
