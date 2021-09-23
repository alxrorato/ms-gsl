package com.dev.gslentrega.entities;

import java.io.Serializable;

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
//@Entity
//@Table(name = "tb_endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -4931084069875888798L;

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) //pra que o id seja gerado automaticamente pelo banco de dados 
	//private Long id;	
	private String logradouro;
	private String bairro;
	private String cidade;
	private UF uf;
	private String cep;
	private Integer numero;
	private String complemento;
	
}
