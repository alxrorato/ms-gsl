package com.dev.gslentrega.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "tb_carga")
public class Carga implements Serializable {
	
	private static final long serialVersionUID = -4418466889386624293L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;	
	private String natureza;
	private Integer quantidade;
	private String especie;
	private Double peso;
	private Double valor;
	private String notaFiscal;
}
