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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Cargas e mercadorias")
public class Carga implements Serializable {
	
	private static final long serialVersionUID = -4418466889386624293L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@ApiModelProperty(name = "id", value = "Indentificador do registro no banco de dados", example = "1")
	private Long id;	

	@ApiModelProperty(name = "natureza", value = "Natureza da carga", example = "granel")
	private String natureza;
	
	@ApiModelProperty(name = "quantidade", value = "Quantidade de componentes da carga", example = "5")
	private Integer quantidade;
	
	@ApiModelProperty(name = "especie", value = "Espécie ou tipo dos componentes da carga", example = "alimentos")
	private String especie;
	
	@NotNull
	@NumberFormat(style = Style.DEFAULT, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "peso", value = "Peso da carga (em Kg)", example = "3")
	private BigDecimal peso;
	
	@NotNull
	@NumberFormat(style = Style.DEFAULT, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "volume", value = "Volume da carga (em m3)", example = "2")
	private BigDecimal volume;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "valor", value = "Valor da carga", example = "57.0")
	private BigDecimal valor;
	
	@ApiModelProperty(name = "notaFiscal", value = "Nota Fiscal da carga", example = "NF0005")
	private String notaFiscal;
	
	@ManyToOne(optional = true)
	//@JoinColumn(name = "id_entrega_fk")
	private Entrega entrega;
}
