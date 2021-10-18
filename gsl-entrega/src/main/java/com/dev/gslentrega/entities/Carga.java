package com.dev.gslentrega.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

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
	@NotNull
	@NumberFormat(style = Style.DEFAULT, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal peso;
	@NotNull
	@NumberFormat(style = Style.DEFAULT, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal volume; // em m3
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal valor;
	private String notaFiscal;
	@ManyToOne(optional = true)
	//@JoinColumn(name = "id_entrega_fk")
	private Entrega entrega;
}
