package com.dev.gslentrega.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Dados do CT-e")
public class Cte {

	@ApiModelProperty(name = "tipoModal", value = "Tipo do modal de transporte que será utilizado na entrega: "
			+ "rodoviário, aeroviário, ferroviário, hidroviário ou dutoviário", example = "rodoviário")
	private String tipoModal;
	
	@ApiModelProperty(name = "tipoCte", value = "Tipo do CT-e: normal, complementar ou de substituição e anulação",
			example = "Normal")
	private String tipoCte;
	
	@ApiModelProperty(name = "tipoServico", value = "Tipo do serviço: normal, redespacho intermediario ou servico vinculado a multimodal",
			example = "Normal")
	private String tipoServico;
	
	@ApiModelProperty(name = "tipoTomadorServico", value = "Tipo do tomador do serviço (quem paga o frete da operação do transporte), "
			+ "podendo ser o remetente, destinatário, recebedor ou outra empresa", example = "Destinatário")
	private String tipoTomadorServico;
	
	@ApiModelProperty(name = "formaPagamento", value = "Forma de pagamento do serviço: a pagar, se ainda não foi pago; ou boleto",
			 example = "A pagar")
	private String formaPagamento; 
   
	@ApiModelProperty(name = "Documento Auxiliar do Conhecimento de Transporte Eletrônico, que \"viaja\" junto com a mercadoria")
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
