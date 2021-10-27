package com.dev.gslentrega.response;

import java.util.List;

import com.dev.gslentrega.enums.UF;

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
public class ComponentesValorPrestacaoServico {
	private List<ComponenteValor> componenteValor;
}
