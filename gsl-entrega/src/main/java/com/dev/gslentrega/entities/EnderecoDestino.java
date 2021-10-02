package com.dev.gslentrega.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_endereco_destino")
public class EnderecoDestino extends Endereco implements Serializable {

	private static final long serialVersionUID = 8738246876823335279L;
	
}
