package com.dev.gslentrega.service;

import java.util.List;

import javax.validation.Valid;

import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.request.EntregaRequest;
import com.dev.gslentrega.request.SolicitacaoRequest;
import com.dev.gslentrega.response.AndamentoEntregaResponse;
import com.dev.gslentrega.response.CancelamentoResponse;
import com.dev.gslentrega.response.Cliente;
import com.dev.gslentrega.response.ConfirmacaoEntregaResponse;
import com.dev.gslentrega.response.OperacaoEtapaResponse;
import com.dev.gslentrega.response.PagamentoResponse;

public interface EntregaService {

	List<Entrega> buscarEntregas();
	
	Entrega buscarEntregaById(Long id);
	
	Cliente getCliente(Long cnpj);

	Entrega buscarEntregaByCodigoSolicitacao(Long codigoSolicitacao);

	AndamentoEntregaResponse findProgressByRequestCode(Long codigoSolicitacao);

	void atualizarEntrega(SolicitacaoRequest solicitacaoRequest);

	Entrega cadastrarEntrega(@Valid EntregaRequest entregaRequest);

	PagamentoResponse efetuarPagamento(Long codigoSolicitacao);

	OperacaoEtapaResponse coletarCarga(Long codigoSolicitacao);

	OperacaoEtapaResponse efetuarRoteirizacao(Long codigoSolicitacao);

	OperacaoEtapaResponse iniciarTransporte(Long codigoSolicitacao);

	OperacaoEtapaResponse distribuirNosCds(Long codigoSolicitacao);

	OperacaoEtapaResponse iniciarLastMile(Long codigoSolicitacao);

	ConfirmacaoEntregaResponse finalizarEntrega(Long codigoSolicitacao);

	CancelamentoResponse cancelarEntrega(Long codigoSolicitacao);

}
