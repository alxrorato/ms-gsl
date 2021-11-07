package com.dev.gslentrega.request;

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
public class DacteRequest implements Serializable {

	private static final long serialVersionUID = 8987669240863813322L;
	
	private String titulo; //Documento Auxiliar do Conhecimento de Transporte Eletrônico
	private int modelo;
	private int serie;
	private Long numero;
	private String folha;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataHoraEmissao;
	private Long inscricaoSulframa;
	private String chaveAcesso; // "33.4444.55.555.444/1111-88-22-000-000.000.111-100.005.111-2"
	private String textoChaveAcesso; // "Consulta de autenticidade no portal nacional do CT-e, no site da Sefaz Autorizadora, ou em http://cte.fazenda.gov.br
	private Long numeroProtocoloAutorizacaoUso; // Random c/ 15 dígitos
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dataHoraGeracaoProtocolo;
}
