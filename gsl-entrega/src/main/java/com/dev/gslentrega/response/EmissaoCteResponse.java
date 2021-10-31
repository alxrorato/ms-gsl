package com.dev.gslentrega.response;

import java.io.Serializable;

import com.dev.gslentrega.enums.FormaPagamento;
import com.dev.gslentrega.enums.TipoCte;
import com.dev.gslentrega.enums.TipoModal;
import com.dev.gslentrega.enums.TipoServico;
import com.dev.gslentrega.enums.TipoTomadorServico;

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
public class EmissaoCteResponse implements Serializable {

	private static final long serialVersionUID = 6959228635049410801L;

	Cte cte;

	private boolean emissaoOk;
	
	private String mensagemDoSfc;
}
