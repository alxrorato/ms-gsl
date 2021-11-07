package com.dev.gslentrega.response;

import java.io.Serializable;

import com.dev.gslentrega.enums.FormaPagamento;
import com.dev.gslentrega.enums.TipoCte;
import com.dev.gslentrega.enums.TipoModal;
import com.dev.gslentrega.enums.TipoServico;
import com.dev.gslentrega.enums.TipoTomadorServico;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Resposta da transação de emissão do CT-e (Conhecimento de Transporte Eletrônico) ao "
		+ "SFC (Sistema de Faturamento e Cobrança)")
public class EmissaoCteResponse implements Serializable {

	private static final long serialVersionUID = 6959228635049410801L;

	@ApiModelProperty(name = "cte", value = "Dados do CT-e")
	Cte cte;

	@ApiModelProperty(name = "emissaoOk", value = "Status da emissão do CT-e ao SFC: true - emissão OK; "
			+ "false - não emitido devido a problema na emissão", example = "true")
	private boolean emissaoOk;
	
	@ApiModelProperty(name = "mensagemDoSfc", value = "Mensagem recebida do SFC acusando o recebimento do CT-e",
			example = "Ct-e recebido com sucesso. Apuração financeira iniciada. Protocolo de recebimento pelo "
					+ "SFC: 208911820879169")
	private String mensagemDoSfc;
}
