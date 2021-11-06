package com.dev.gslentrega.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Requisição para solicitação de uma entrega")
public class EntregaRequest {

	@ApiModelProperty(name = "cnpjCliente", value = "CNPJ do cliente solicitante", 
			required = true, example = "80200396000150")
	private Long cnpjCliente;

	@ApiModelProperty(name = "tipoDocumentoDestinatario", value = "Tipo do documento do destinatario, que pode ser CPF ou CNPJ", 
			required = true, example = "CPF")
	private String tipoDocumentoDestinatario;
	
	@ApiModelProperty(name = "documentoDestinatario", value = "CPF ou CNPJ do destinatário", required = true, example = "27365467096")
	private Long documentoDestinatario;
	
	@ApiModelProperty(name = "enderecoOrigem", value = "Endereço de origem do transporte")
	private EnderecoRequest enderecoOrigem;
	
	@ApiModelProperty(name = "enderecoDestino", value = "Endereço de destino do transporte")
	private EnderecoRequest enderecoDestino;
	
	@ApiModelProperty(name = "enderecoDestino", value = "Carga contendo as informações das mercadorias a serem transportadas")
	private List<CargaRequest> cargaRequests;

	@ApiModelProperty(name = "naturezaPrestacao", value = "Natureza da prestação do serviço", 
			required = true, example = "16556 - Transporte a estabelecimento comercial")
	private String naturezaPrestacao;
	
	@ApiModelProperty(name = "observacoes", value = "Qualquer informação que o solicitante julgar relevante", 
			example = "Carga contém produtos perecíveis")
	private String observacoes;
	
}
