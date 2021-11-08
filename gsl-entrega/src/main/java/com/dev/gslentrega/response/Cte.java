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
   
	@ApiModelProperty(name = "dacte", value = "Documento Auxiliar do Conhecimento de Transporte Eletrônico, que \"viaja\" junto com a mercadoria")
	private Dacte dacte;
	
	@ApiModelProperty(name = "naturezaPrestacao", value = "Dados referente a natureza da prestação do serviço")
	private NaturezaPrestacao naturezaPrestacao; 
	
	@ApiModelProperty(name = "dadosEmitente", value = "Dados do emitente (empresa que emite o CT-e), que geralmente é a transportadora responsável"
			+ " pelo gerenciamento da operação de transporte")
	private DadosAtorCte dadosEmitente; // razaoSocial e endereco da Boa Entrega: 

	@ApiModelProperty(name = "dadosRemetente", value = "Dados do remetente, que é a empresa responsável por enviar a mercadoria, geralmente o "
			+ "próprio emissor da NFe, neste caso o cliente da Boa Entrega")
	private DadosAtorCte dadosRemetente; //razaoSocial e endereco
	
	@ApiModelProperty(name = "dadosDestinatario", value = "Dados do destinatário, que é quem recebe a mercadoria ao final do trajeto de transporte, "
			+ "podendo ser uma pessoa física ou jurídica")
	private DadosAtorCte dadosDestinatario; //nomeRazaoSocial e endereco
	
	@ApiModelProperty(name = "dadosExpedidor", value = "Dados do expedidor, que é o responsável por entregar a carga ao transportador quando envio "
			+ "não for realizado pelo remetente. O expedidor pode ser uma empresa de logística ou outra empresa de transporte que intermediará a operação.")
	private DadosAtorCte dadosExpedidor;
	
	@ApiModelProperty(name = "dadosRecebedor", value = "Dados do recebedor, que é quem recebe a mercadoria como um intermediário entre o emitente e o"
			+ " destinatário final, sendo o responsável por receber a carga do transportador.")
	private DadosAtorCte dadosRecebedor;
	
	@ApiModelProperty(name = "dadosTomador", value = "Dados do tomador, que é quem paga o frete da operação do transporte, pode ser o remetente, "
			+ "destinatário, recebedor ou outra empresa")
	private DadosAtorCte dadosTomador;

	@ApiModelProperty(name = "dadosCarga", value = "Dados da carga a ser transportada")
	private DadosCarga dadosCarga;
	
	@ApiModelProperty(name = "dadosSeguroCarga", value = "Dados do seguro da carga a ser transportada")
	private DadosSeguroCarga dadosSeguroCarga;
	
	@ApiModelProperty(name = "componentesValorPrestacaoServico", value = "Componentes do valor da prestação de serviços")
	private ComponentesValorPrestacaoServico componentesValorPrestacaoServico;
	
}
