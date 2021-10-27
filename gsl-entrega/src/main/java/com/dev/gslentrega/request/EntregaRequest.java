package com.dev.gslentrega.request;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.dev.gslentrega.entities.Carga;
import com.dev.gslentrega.entities.EnderecoDestino;
import com.dev.gslentrega.entities.EnderecoOrigem;
import com.dev.gslentrega.enums.StatusEntrega;
import com.dev.gslentrega.enums.StatusPagamento;
import com.dev.gslentrega.enums.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntregaRequest {

	private Long cnpjCliente;
	private String tipoDocumentoDestinatario; // "CPF" ou "CNPJ"
	private Long documentoDestinatario;
	private EnderecoRequest enderecoOrigem;
	private EnderecoRequest enderecoDestino;
	//private LocalDateTime dataSolicitacao;
	private List<CargaRequest> cargaRequests/*cargas*/;
	private String naturezaPrestacao; // Ex.: 16556 - Transporte a estabelecimento comercial 
	//private String situacaoTributaria; //Ex.: 00 - Tributação normal do ICMS
	//private Double baseCalculoImposto; // == valor total do serviço
	//private Double aliquotaIcms; // Ex.: 7%
	//private Double valorIcms; // baseCalculoImposto * aliquotaIcms / 100
	private String observacoes;
	
}
