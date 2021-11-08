package com.dev.gslentrega.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Dados principais do seguro da carga")
public class DadosSeguroCarga {

	@ApiModelProperty(name = "nomeSeguradora", value = "Nome da seguradora onde a carga está segurada", example = "Porto Seguro S.A.")
	private String nomeSeguradora;
	
	@ApiModelProperty(name = "nomeResponsavel", value = "Nome do responsável na seguradora (pode ser o corretor)", example = "Beatriz Fernandes Carvalho")
	private String nomeResponsavel;
	
	@ApiModelProperty(name = "numeroApolice", value = "Número da apólice do seguro", example = "7989457344")
	private Long numeroApolice;
	
	@ApiModelProperty(name = "numeroAverbacao", value = "Número da averbação (apólice que protege o transportador em caso de acidentes e envolve "
			+ "um procedimento obrigatório chamado averbação de carga)", example = "1696504")
	private Long numeroAverbacao;
}
