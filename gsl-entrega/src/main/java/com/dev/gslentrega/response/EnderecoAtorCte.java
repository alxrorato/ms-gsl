package com.dev.gslentrega.response;

import java.io.Serializable;

import com.dev.gslentrega.enums.UF;

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
@ApiModel(description = "Dados de endereço de um ator do CT-e")
public class EnderecoAtorCte implements Serializable {

	private static final long serialVersionUID = -2515059053089546417L;
	
	@ApiModelProperty(name = "logradouro", value = "Endereço sem o número", example = "Rua Duque de Caxias")
	private String logradouro;

	@ApiModelProperty(name = "numero", value = "Número referente ao endereço", example = "225")
	private String numero;
	
	@ApiModelProperty(name = "complemento", value = "Complemento do endereço, se necessário", example = "galpão 2")
	private String complemento;

	@ApiModelProperty(name = "bairro", value = "Bairro referente ao endereço", example = "Centro")
	private String bairro;
	
	@ApiModelProperty(name = "cidade", value = "Cidade referente ao endereço", example = "São Paulo")
	private String cidade;
	
	@ApiModelProperty(name = "uf", value = "Sigla do Estado da cidade", example = "SP")
	private UF uf;
	
	@ApiModelProperty(name = "cep", value = "Código de endereçamento postal referente ao endereço", example = "01958-050")
	private String cep;
	
}
