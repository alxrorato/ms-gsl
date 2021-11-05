package com.dev.gslentrega.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.dev.gslentrega.enums.StatusEntrega;
import com.dev.gslentrega.enums.StatusPagamento;
import com.dev.gslentrega.enums.TipoDocumento;
import com.dev.gslentrega.enums.TipoModal;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "tb_entrega", uniqueConstraints = 
	@UniqueConstraint(columnNames = "codigoSolicitacao", name = "cod_solic_uk"))
@ApiModel(description = "Resposta da consulta a uma entrega")
public class Entrega implements Serializable {

	private static final long serialVersionUID = -6034382672775937921L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pra que o id seja gerado automaticamente pelo banco de dados 
	@ApiModelProperty(name = "id", value = "identificador da entrega (uso interno")
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataSolicitacao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDateTime dataPrevisao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataFinalizacao;
	
	private BigDecimal distanciaTotal;
	
	private BigDecimal distanciaPercorrida;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEntrega statusEntrega;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataStatusEntrega;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataAlteracao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataCancelamento;
	
	private String motivoCancelamento;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal valorTotal;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPagamento statusPagamento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataPagamento;
	
	@OneToMany(/*mappedBy = "entrega", */fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Carga> cargas;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal valorFrete;   
	
	private String naturezaPrestacao; // Ex.: 16556 - Transporte a estabelecimento comercial 
	
	private String situacaoTributaria; //Ex.: 00 - Tributação normal do ICMS
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal baseCalculoImposto; // == valor total do serviço
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal aliquotaIcms; // Ex.: 7%
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal valorIcms; // baseCalculoImposto * aliquotaIcms / 100
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	private BigDecimal valorTotalSeguroCarga;
	
	private Long numeroApoliceSeguroCarga;
	
	private Long numeroAverbacaoSeguroCarga;
	
	private boolean entregaEmParceria;
	
	private Long cnpjParceira;
	
	private String razaoSocialParceira;
	
	private String nomeComercialParceira;

	private String inscricaoEstadualParceira;
	
	private String telefoneParceira;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_parceira_id_fk")
	private EnderecoParceira enderecoParceira;
	
	private String recebedorEntrega;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoModal tipoModal;

	private boolean cteEmitido;

	private String observacoes;
	
}
