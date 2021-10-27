package com.dev.gslentrega.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dev.gslentrega.enums.TipoModal;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmissaoCteResponse implements Serializable {

	private static final long serialVersionUID = 6959228635049410801L;

	private TipoModal tipoModal;
	
	private String tipoCte;
	private String tipoServico; //Ex.: normal, redespacho intermediario, servico vinculado a multimodal
	private String tipoTomadorServico; // quem paga o frete da operação do transporte, pode ser o remetente, destinatário, recebedor ou outra empresa
	private String formaPagamento; 
	
	private Dacte dacte;
	
	private NaturezaPrestacao naturezaPrestacao; 
	
	//Empresa que emite o documento do CTe, geralmente é a transportadora responsável pelo gerenciamento da operação de transporte.
	private DadosAtorCte dadosEmitente; // razaoSocial e endereco da Boa Entrega: 

	// Responsável por enviar a mercadoria, geralmente o próprio emissor da NFe, no caso o cliente da Boa Entrega
	private DadosAtorCte dadosRemetente; //razaoSocial e endereco
	
	// Quem recebe a mercadoria ao final do trajeto de transporte, pode ser uma pessoa física ou jurídica
	private DadosAtorCte dadosDestinatario; //nomeRazaoSocial e endereco
	
	/* É responsável por entregar a carga ao transportador quando envio não for realizado pelo remetente. 
	 * O expedidor pode ser uma empresa de logística ou outra empresa de transporte que intermediará a operação.
	 */
	private DadosAtorCte dadosExpedidor;
	
	 /* É quem recebe a mercadoria como um intermediário entre o emitente e o destinatário final, 
	  * sendo o responsável por receber a carga do transportador.	
	  */
	private DadosAtorCte dadosRecebedor;
	// quem paga o frete da operação do transporte, pode ser o remetente, destinatário, recebedor ou outra empresa
	private DadosAtorCte dadosTomador;

	private String produtoPredominante; //combustivel
	
	private String outrasCaracteristicasCarga; //granel
	
	private BigDecimal valorTotalMercadoria; // soma dos valores de cada carga
	
	private BigDecimal pesoBruto;
	private BigDecimal pesoAferido;
	private BigDecimal cubagemM3; //pesoCubado
	private BigDecimal volume;
	
	private DadosSeguroCarga dadosSeguroCarga; //nomeResponsavel, numeroApolice e numeroAverbacao
	
	private ComponentesValorPrestacaoServico componentesValorPrestacaoServico;
	
}
