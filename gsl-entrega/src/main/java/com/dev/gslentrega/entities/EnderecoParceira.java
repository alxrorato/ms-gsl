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
public class EnderecoParceira extends Endereco implements Serializable {

	private static final long serialVersionUID = -1417184022215505884L;
	
}
