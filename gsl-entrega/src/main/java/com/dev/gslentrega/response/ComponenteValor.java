package com.dev.gslentrega.response;

import java.math.BigDecimal;

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
@ApiModel(description = "Componente da prestação de serviços")
public class ComponenteValor {
	
	@ApiModelProperty(name = "nome", value = "Nome do componente", example = "Frete Valor")
	private String nome;
	
	@ApiModelProperty(name = "valor", value = "Valor do componente", example = "6750.00")
	private BigDecimal valor;
}
