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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "Natureza da prestação do serviço")
public class NaturezaPrestacao {

	@ApiModelProperty(name = "codigoNomeNaturezaPrestacao", value = "Código e nome da natureza da prestação do serviço",
			 example = "16556 - Transporte a estabelecimento comercial")
	private String codigoNomeNaturezaPrestacao;
	
	@ApiModelProperty(name = "localInicioPrestacao", value = "Local (cidade - UF) de início da prestação do serviço",
			 example = "São Paulo - SP")
	private String localInicioPrestacao;
	
	@ApiModelProperty(name = "localTerminoPrestacao", value = "Local (cidade - UF) para finalização da prestação do serviço",
			 example = "Fortaleza - CE")
	private String localTerminoPrestacao;

}
