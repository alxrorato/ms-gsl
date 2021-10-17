package com.dev.gslentrega.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.dev.gslentrega.enums.StatusEntrega;
import com.dev.gslentrega.enums.StatusPagamento;
import com.dev.gslentrega.enums.TipoDocumento;

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
@Table(name = "tb_entrega", uniqueConstraints = 
	@UniqueConstraint(columnNames = "codigoSolicitacao", name = "cod_solic_uk"))
public class Entrega implements Serializable {

	private static final long serialVersionUID = -6034382672775937921L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pra que o id seja gerado automaticamente pelo banco de dados 
	private Long id;
	private Long codigoSolicitacao; 
	private Long cnpjCliente;
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipoDocumentoDestinatario;
	private Long documentoDestinatario;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_origem_id_fk")
	private EnderecoOrigem enderecoOrigem;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_destino_id_fk")
	private EnderecoDestino enderecoDestino;
	@NotNull
	private LocalDateTime dataSolicitacao;
	private LocalDate dataPrevisao;
	private LocalDateTime dataConclusao;
	private int distanciaTotal;
	private int distanciaPercorrida;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEntrega statusEntrega;
	private LocalDateTime dataStatusEntrega;
	private Date dataAlteracao;
	private Date dataExclusao;
	private Double valorTotal;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPagamento statusPagamento;
	@OneToMany(/*mappedBy = "entrega", */fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Carga> cargas;
	private Double valorFrete;   
	private String naturezaPrestacao; // Ex.: 16556 - Transporte a estabelecimento comercial 
	private String situacaoTributaria; //Ex.: 00 - Tributação normal do ICMS
	private Double baseCalculoImposto; // == valor total do serviço
	private Double aliquotaIcms; // Ex.: 7%
	private Double valorIcms; // baseCalculoImposto * aliquotaIcms / 100
	private Double valorTotalSeguroCarga;
	private boolean entregaEmParceria;
	private Long cnpjParceira;
	private String razaoSocialParceira;
	private String nomeComercialParceira;
	private String observacoes;
	
}
