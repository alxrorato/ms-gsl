package com.dev.gslentrega.service;

import java.util.List;

import com.dev.gslentrega.entities.Entrega;

public interface EntregaService {

	List<Entrega> buscarEntregas();
	
	Entrega buscarEntregaById(Long id);
}
