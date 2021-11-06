package com.dev.gslentrega.response;

import java.io.Serializable;

import com.dev.gslentrega.enums.UF;

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
public class Endereco implements Serializable {

	private static final long serialVersionUID = -2515059053089546417L;
	
	@ApiModelProperty(name = "id", value = "Identificador do registro do endereço no banco de dados", example = "1")
	private Long id;	
	
	@ApiModelProperty(name = "logradouro", value = "Endereço sem o número", example = "Rua 13 de Maio")
	private String logradouro;

	@ApiModelProperty(name = "numero", value = "Número referente ao endereço", example = "111")
	private String numero;
	
	@ApiModelProperty(name = "complemento", value = "Complemento do endereço, se necessário", example = "casa 5")
	private String complemento;

	@ApiModelProperty(name = "bairro", value = "Bairro referente ao endereço", example = "Centro")
	private String bairro;
	
	@ApiModelProperty(name = "cidade", value = "Cidade referente ao endereço", example = "Curitiba")
	private String cidade;
	
	@ApiModelProperty(name = "uf", value = "Sigla do Estado da cidade", example = "PR")
	private UF uf;
	
	@ApiModelProperty(name = "cep", value = "Código de endereçamento postal referente ao endereço", example = "82500120")
	private String cep;
	
}
