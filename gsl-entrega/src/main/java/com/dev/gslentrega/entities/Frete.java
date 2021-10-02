package com.dev.gslentrega.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "tb_frete")
public class Frete implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private Double valorImpostoCliente;
	private Double valorPedagio;
	private Double valorFretePeso;

}
