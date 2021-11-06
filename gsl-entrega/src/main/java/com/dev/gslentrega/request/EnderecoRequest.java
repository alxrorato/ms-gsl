package com.dev.gslentrega.request;

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
@ApiModel(description = "Dados de endereço")
public class EnderecoRequest {
	
	@ApiModelProperty(name = "logradouro", value = "Endereço sem o número", required = true, example = "Rua XV de Novembro")
	private String logradouro;
	
	@ApiModelProperty(name = "numero", value = "Número referente ao endereço", required = true, example = "100")
	private String numero;
	
	@ApiModelProperty(name = "bairro", value = "Bairro referente ao endereço", required = true, example = "Centro")
	private String bairro;
	
	@ApiModelProperty(name = "complemento", value = "Complemento do endereço, se necessário", example = "esquina")
	private String complemento;

	@ApiModelProperty(name = "cidade", value = "Cidade referente ao endereço", required = true, example = "Manaus")
	private String cidade;
	
	@ApiModelProperty(name = "uf", value = "Unidade da Federação à qual pertence a cidade", required = true, example = "AM")
	private String uf;
	
	@ApiModelProperty(name = "cep", value = "Código de endereçamento postal referente ao endereço", 
			required = true, example = "69000001")
	private String cep;
}
