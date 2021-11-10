package com.dev.gslcliente.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Dados para cadastro do endereço do cliente")
public class EnderecoRequest {
	
	@ApiModelProperty(name = "logradouro", value = "Endereço sem o número", required = true, example = "Rua Felipe Schmidt")
	private String logradouro;

	@ApiModelProperty(name = "numero", value = "Número referente ao endereço", required = true, example = "32")
	private String numero;
	
	@ApiModelProperty(name = "complemento", value = "Complemento do endereço", example = "lado A")
	private String complemento;
	
	@ApiModelProperty(name = "bairro", value = "Bairro referente ao endereço", example = "Centro")
	private String bairro;
	
	@ApiModelProperty(name = "cidade", value = "Cidade referente ao endereço", required = true, example = "Florianopolis")
	private String cidade;
	
	@ApiModelProperty(name = "uf", value = "Sigla do Estado da cidade", required = true, example = "SC")
	private String uf;
	
	@ApiModelProperty(name = "cep", value = "Código de endereçamento postal referente ao endereço", 
			required = true, example = "88010001")
	private String cep;

}
