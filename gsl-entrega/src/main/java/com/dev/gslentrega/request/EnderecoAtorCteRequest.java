package com.dev.gslentrega.request;

import java.io.Serializable;

import com.dev.gslentrega.enums.UF;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnderecoAtorCteRequest implements Serializable {

	private static final long serialVersionUID = -685211799016106172L;
	
	private String logradouro;
	private String bairro;
	private String cidade;
	private UF uf;
	private String cep;
	private String numero;
	private String complemento;
}
