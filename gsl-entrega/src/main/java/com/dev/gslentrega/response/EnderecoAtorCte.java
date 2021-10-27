package com.dev.gslentrega.response;

import java.io.Serializable;

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
public class EnderecoAtorCte implements Serializable {

	private static final long serialVersionUID = -2515059053089546417L;
	
	private String logradouro;
	private String bairro;
	private String cidade;
	private UF uf;
	private String cep;
	private String numero;
	private String complemento;
}
