package com.dev.gslentrega.response;

import java.time.LocalDateTime;

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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "Dados do DACTE")
public class Dacte {

	@ApiModelProperty(name = "titulo", value = "Título do Dacte", example = "Documento Auxiliar do "
			+ "Conhecimento de Transporte Eletrônico")
	private String titulo;
	
	@ApiModelProperty(name = "modelo", value = "Modelo do Dacte", example = "57")
	private int modelo;
	
	@ApiModelProperty(name = "serie", value = "Série do Dacte", example = "1")
	private int serie;
	
	@ApiModelProperty(name = "numero", value = "Número do Dacte", example = "1")
	private Long numero;
	
	@ApiModelProperty(name = "folha", value = "Número da folha do Dacte", example = "1/1")
	private String folha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@ApiModelProperty(name = "dataHoraEmissao", value = "Data/hora da emissão do Dacte", example = "06-11-2021 10:43:43")
	private LocalDateTime dataHoraEmissao;
	
	@ApiModelProperty(name = "inscricaoSulframa", value = "Número de inscrição na Sulframa, se for o caso", example = "90068714")
	private Long inscricaoSulframa;
	
	@ApiModelProperty(name = "chaveAcesso", value = "Chave de acesso ao portal nacional do CT-e para consulta de autenticidade", 
			example = "33.4444.55.555.444/1111-88-22-000-000.000.111-100.005.111-2")
	private String chaveAcesso;
	
	@ApiModelProperty(name = "textoChaveAcesso", value = "Texto impresso no Dacte logo abaixo da chave de acesso", 
			example = "Consulta de autenticidade no portal nacional do CT-e, no site da Sefaz Autorizadora, ou em http://cte.fazenda.gov.br")
	private String textoChaveAcesso;
	
	@ApiModelProperty(name = "numeroProtocoloAutorizacaoUso", value = "Protocolo de autorização de uso", example = "328448663319735")
	private Long numeroProtocoloAutorizacaoUso; // Random c/ 15 dígitos
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@ApiModelProperty(name = "dataHoraGeracaoProtocolo", value = "Data/hora da geração do protocolo", example = "06-11-2021 10:43:43")
	private LocalDateTime dataHoraGeracaoProtocolo;
}
