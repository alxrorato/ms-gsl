package com.dev.gslentrega.response;

import java.util.List;

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
@ApiModel(description = "Lista dos componentes da prestação de serviços")
public class ComponentesValorPrestacaoServico {
	
	@ApiModelProperty(name = "componenteValor", value = "Componentes e respectivos valores envolvidos na prestação do serviço")
	private List<ComponenteValor> componenteValor;
}
