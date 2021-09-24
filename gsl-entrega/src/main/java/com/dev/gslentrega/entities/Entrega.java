package com.dev.gslentrega.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "tb_entrega")
public class Entrega implements Serializable {

	private static final long serialVersionUID = -6034382672775937921L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pra que o id seja gerado automaticamente pelo banco de dados 
	private Long id;
	private Long codigoSolicitacao;
	private Long codigoCliente;
	private String enderecoOrigem;
	private String enderecoDestino;
	//@OneToMany(mappedBy = "entrega")
	//private List<Endereco> enderecos;
	private String descricaoCarga;
	private Date dataSolicitacao;
	private Date dataPrevisao;
	private Date dataConclusao;
	private String statusEntrega;
	private Date dataAlteracao;
	private Date dataExclusao;
	private Double valor;
	private String statusPagamento;
	private String observacoes;

}
