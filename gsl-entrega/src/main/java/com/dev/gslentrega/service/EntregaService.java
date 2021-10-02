package com.dev.gslentrega.service;

import java.util.List;

import javax.validation.Valid;

import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.request.EntregaRequest;
import com.dev.gslentrega.response.Cliente;

public interface EntregaService {

	List<Entrega> buscarEntregas();
	
	Entrega buscarEntregaById(Long id);
	
	Cliente getCliente(Long cnpj);

	Entrega cadastrarEntrega(@Valid EntregaRequest entregaRequest);
}
