package com.dev.gslsfc.request;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class CteRequest implements Serializable {

	private static final long serialVersionUID = 1531447049276527785L;

	private String tipoModal;
	
	private String tipoCte;
	private String tipoServico; //Ex.: normal, redespacho intermediario, servico vinculado a multimodal
	private String tipoTomadorServico; // quem paga o frete da operação do transporte, pode ser o remetente, destinatário, recebedor ou outra empresa
	private String formaPagamento; 
	
	private DacteRequest dacte;
	
	private NaturezaPrestacaoRequest naturezaPrestacao; 
	
	//Empresa que emite o documento do CTe, geralmente é a transportadora responsável pelo gerenciamento da operação de transporte.
	private DadosAtorCteRequest dadosEmitente; // razaoSocial e endereco da Boa Entrega: 

	// Responsável por enviar a mercadoria, geralmente o próprio emissor da NFe, no caso o cliente da Boa Entrega
	private DadosAtorCteRequest dadosRemetente; //razaoSocial e endereco
	
	// Quem recebe a mercadoria ao final do trajeto de transporte, pode ser uma pessoa física ou jurídica
	private DadosAtorCteRequest dadosDestinatario; //nomeRazaoSocial e endereco
	
	/* É responsável por entregar a carga ao transportador quando envio não for realizado pelo remetente. 
	 * O expedidor pode ser uma empresa de logística ou outra empresa de transporte que intermediará a operação.
	 */
	private DadosAtorCteRequest dadosExpedidor;
	
	 /* É quem recebe a mercadoria como um intermediário entre o emitente e o destinatário final, 
	  * sendo o responsável por receber a carga do transportador.	
	  */
	private DadosAtorCteRequest dadosRecebedor;
	
	// quem paga o frete da operação do transporte, pode ser o remetente, destinatário, recebedor ou outra empresa
	private DadosAtorCteRequest dadosTomador;

	private DadosCargaRequest dadosCarga;
	
	private DadosSeguroCargaRequest dadosSeguroCarga; //nomeResponsavel, numeroApolice e numeroAverbacao
	
	private ComponentesValorPrestacaoServicoRequest componentesValorPrestacaoServico;
	
}
