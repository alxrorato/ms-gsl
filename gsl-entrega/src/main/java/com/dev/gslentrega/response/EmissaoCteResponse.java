package com.dev.gslentrega.response;

import java.io.Serializable;

import com.dev.gslentrega.enums.FormaPagamento;
import com.dev.gslentrega.enums.TipoCte;
import com.dev.gslentrega.enums.TipoModal;
import com.dev.gslentrega.enums.TipoServico;
import com.dev.gslentrega.enums.TipoTomadorServico;

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

	private String tipoModal;
	
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

	private DadosCarga dadosCarga;
	
	private DadosSeguroCarga dadosSeguroCarga; //nomeResponsavel, numeroApolice e numeroAverbacao
	
	private ComponentesValorPrestacaoServico componentesValorPrestacaoServico;
	
}
