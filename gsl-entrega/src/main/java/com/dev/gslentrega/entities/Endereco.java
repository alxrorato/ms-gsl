package com.dev.gslentrega.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -4931084069875888798L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pra que o id seja gerado automaticamente pelo banco de dados 
	private Long id;	
	private String logradouro;
	private String bairro;
	private String cidade;
	private UF uf;
	private String cep;
	private Integer numero;
	private String complemento;
	private TipoEndereco endereco;
	@ManyToOne // Sempre se lê da direita p/ a esquerda: uma entrega pode ter vários endereços
	@JoinColumn(name = "id_entrega_fk")
	private Entrega entrega;
	
}
