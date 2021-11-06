package com.dev.gslentrega.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dev.gslentrega.enums.UF;

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
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -2515059053089546417L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@ApiModelProperty(name = "id", value = "Identificado do endereço no banco de dados", example = "1")
	private Long id;	
	
	@NotBlank
	@Size(min = 3, max = 100)
	@Column(nullable = false)
	@ApiModelProperty(name = "logradouro", value = "Endereço sem o número", example = "Rua XV de Novembro")
	private String logradouro;

	@NotNull(message = "{NotNull.endereco.numero}")
	@Column(nullable = false, length = 10)
	@ApiModelProperty(name = "numero", value = "Número referente ao endereço", example = "111")
	private String numero;
	
	@Size(max = 30)
	@ApiModelProperty(name = "complemento", value = "Complemento do endereço, se necessário", example = "esquina")
	private String complemento;
	
	@NotBlank
	@Size(min = 3, max = 30)
	@Column(nullable = false)
	@ApiModelProperty(name = "bairro", value = "Bairro referente ao endereço", example = "Centro")
	private String bairro;

	@NotBlank
	@Size(min = 3, max = 30)
	@Column(nullable = false)
	@ApiModelProperty(name = "cidade", value = "Cidade referente ao endereço", example = "Parintins")
	private String cidade;
	
	@NotNull(message = "{NotNull.endereco.uf}")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(name = "uf", value = "Sigla do Estado da cidade", example = "AM")
	private UF uf;

	@NotBlank
	@Size(min = 8, max = 9, message = "{Size.endereco.cep}")
	@Column(nullable = false, length = 9)
	@ApiModelProperty(name = "cep", value = "Código de endereçamento postal referente ao endereço", example = "69000001")
	private String cep;
}
