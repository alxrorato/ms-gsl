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
@ApiModel(description = "Resposta da solicitação de uma uma entrega")
public class Entrega implements Serializable {

	private static final long serialVersionUID = -6034382672775937921L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pra que o id seja gerado automaticamente pelo banco de dados 
	@ApiModelProperty(name = "id", value = "Identificador do registro da entrega no banco de dados", example = "1")
	private Long id;
	
	@ApiModelProperty(name = "codigoSolicitacao", 
			value = "Código de solicitação gerado para o cliente após a conclusão da solicitação de uma entrega e que serve para rastreio da entrega",
			example = "116922963214")
	private Long codigoSolicitacao;
	
	@ApiModelProperty(name = "cnpjCliente", value = "CNPJ do cliente solicitante", example = "80200396000150")
	private Long cnpjCliente;
	
	@ApiModelProperty(name = "tipoDocumentoDestinatario", value = "Tipo do documento do destinatario, que pode ser CPF ou CNPJ", 
			example = "CPF")
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipoDocumentoDestinatario;
	
	@ApiModelProperty(name = "documentoDestinatario", value = "CPF ou CNPJ do destinatário", example = "27365467096")
	private Long documentoDestinatario;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_origem_id_fk")
	@ApiModelProperty(name = "enderecoOrigem", value = "Endereço de origem do transporte")
	private EnderecoOrigem enderecoOrigem;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_destino_id_fk")
	@ApiModelProperty(name = "enderecoDestino", value = "Endereço de destino do transporte")
	private EnderecoDestino enderecoDestino;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(name = "dataSolicitacao", value = "Data/hora da solicitação da entrega", example = "05-11-2021 12:09:29")
	private LocalDateTime dataSolicitacao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@ApiModelProperty(name = "dataPrevisao", value = "Data prevista para entrega", example = "06-11-2021")
	private LocalDateTime dataPrevisao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(name = "dataFinalizacao", value = "Data/hora da entrega ao destinatário final", example = "06-11-2021 19:08:45")
	private LocalDateTime dataFinalizacao;
	
	@ApiModelProperty(name = "distanciaTotal", value = "Distância (em Km) entre o enderelo de origem e o de destino", example = "67.41")
	private BigDecimal distanciaTotal;
	
	@ApiModelProperty(name = "distanciaPercorrida", value = "Distância (em Km) percorrida até o momento", example = "16.00")
	private BigDecimal distanciaPercorrida;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(name = "statusEntrega", value = "Situação do andamento da entrega (SOLICITAÇÃO, COLETA, ROTEIRIZAÇÃO, "
			+ "TRANSPORTE, DISTRIBUIÇÃO, LAST_MILE, FINALIZADA ou CANCELADA", example = "SOLICITACAO")
	private StatusEntrega statusEntrega;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(name = "dataStatusEntrega", value = "Data/hora da atualização do status da entrega", example = "06-11-2021 19:08:45")
	private LocalDateTime dataStatusEntrega;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(name = "dataAlteração", value = "Data/hora de atualização deste registro no banco de dados", example = "06-11-2021 19:15:50")
	private LocalDateTime dataAlteracao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(name = "dataCancelamento", value = "Data/hora do cancelamento da solicitação da entrega", example = "06-11-2021 15:17:12")
	private LocalDateTime dataCancelamento;
	
	@ApiModelProperty(name = "motivoCancelamento", value = "Data/hora do cancelamento da solicitação da entrega", example = "Desistência por parte do cliente")
	private String motivoCancelamento;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "valorTotal", value = "Valor total do serviço de entrega", example = "4500.0")
	private BigDecimal valorTotal;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(name = "statusPagamento", value = "Situação do pagamento do serviço de entrega (pendente ou confirmado)", 
		example = "CONFIRMADO")
	private StatusPagamento statusPagamento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(name = "dataPagamento", value = "Data/hora do pagamento do serviço de entrega", example = "06-11-2021 16:22:31")
	private LocalDateTime dataPagamento;
	
	@OneToMany(/*mappedBy = "entrega", */fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ApiModelProperty(name = "cargas", value = "Lista das cargas a serem transportadas")
	private List<Carga> cargas;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "valorFrete", value = "Valor do frete referente a esta entrega", example = "4500.0")
	private BigDecimal valorFrete;   
	
	@ApiModelProperty(name = "naturezaPrestacao", value = "Natureza da prestação do serviço", 
			example = "16556 - Transporte a estabelecimento comercial")
	private String naturezaPrestacao; 
	
	@ApiModelProperty(name = "situacaoTributaria", value = "Situação tributária da prestação do serviço", 
			example = "00 - Tributação normal do ICMS")
	private String situacaoTributaria;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "baseCalculoImposto", value = "Base de cálculo para fins de imposto ICMS", 
			example = "4500.0")
	private BigDecimal baseCalculoImposto; // == valor total do serviço
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "aliquotaIcms", value = "Alíquota (em %) para fins de cálculo do ICMS, aplicada sobre a base de cálculo", 
			example = "12")
	private BigDecimal aliquotaIcms;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "valorIcms", value = "Valor do imposto ICMS (baseCalculo * aliquotaIcms / 100)", 
		example = "540.0")
	private BigDecimal valorIcms;
	
	@NotNull
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
	@ApiModelProperty(name = "valorTotalSeguroCarga", value = "Valor total do seguro contratato para transporte da carga", 
		example = "30.22")
	private BigDecimal valorTotalSeguroCarga;
	
	@ApiModelProperty(name = "numeroApoliceSeguroCarga", value = "Número da apólice referente ao seguro contratado", 
			example = "3052397520")
	private Long numeroApoliceSeguroCarga;
	
	@ApiModelProperty(name = "numeroAverbacaoSeguroCarga", value = "Número da averbação referente ao seguro contratado", 
			example = "1015068")
	private Long numeroAverbacaoSeguroCarga;
	
	@ApiModelProperty(name = "entregaEmParceria", value = "Indicativo de que a entrega será ou foi realizada em parceria (true ou false)", 
			example = "true")
	private boolean entregaEmParceria;
	
	@ApiModelProperty(name = "cnpjParceira", value = "CNPJ da transportadora parceira da Boa Entrega", 
			example = "32614634000120")
	private Long cnpjParceira;
	
	@ApiModelProperty(name = "razaoSocialParceira", value = "Razão social da transportadora parceira da Boa Entrega", 
			example = "Transportadora Entrega Rápida Ltda.")
	private String razaoSocialParceira;
	
	@ApiModelProperty(name = "nomeComercialParceira", value = "Nome comercial da transportadora parceira da Boa Entrega", 
			example = "Transportadora Entrega Rápida")
	private String nomeComercialParceira;

	@ApiModelProperty(name = "inscricaoEstadualParceira", value = "Inscrição estadual da transportadora parceira da Boa Entrega", 
			example = "67.375.056-6")
	private String inscricaoEstadualParceira;
	
	@ApiModelProperty(name = "telefoneParceira", value = "Número do telefone da transportadora parceira da Boa Entrega", 
			example = "(11)5773-8532")
	private String telefoneParceira;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_parceira_id_fk")
	@ApiModelProperty(name = "enderecoParceira", value = "Endereço da transportadora parceira da Boa Entrega")
	private EnderecoParceira enderecoParceira;
	
	@ApiModelProperty(name = "recebedorEntrega", value = "Nome de quem recebeu a mercadoria no momento da entrega no destino final",
			example = "Pedro Ribeiro Carvalho")
	private String recebedorEntrega;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(name = "tipoModal", value = "Tipo do modal utilizado no transporte: rodoviário, aeroviário, ferroviário, "
			+ "hidroviário ou dutoviário",	example = "RODOVIARIO")
	private TipoModal tipoModal;

	@ApiModelProperty(name = "cteEmitido", value = "Indicador de emissão ou não do CT-e: true - já emitido; false - ainda não emitido",
			example = "true")
	private boolean cteEmitido;

	@ApiModelProperty(name = "observacoes", value = "Qualquer informação que o solicitante julgar relevante", 
			example = "Carga contém produtos perecíveis")
	private String observacoes;
	
}
