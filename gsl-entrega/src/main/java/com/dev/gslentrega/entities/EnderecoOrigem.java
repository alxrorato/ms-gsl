package com.dev.gslentrega.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_endereco_origem")
public class EnderecoOrigem extends Endereco implements Serializable {

	private static final long serialVersionUID = -8770957117209951046L;
	
}
