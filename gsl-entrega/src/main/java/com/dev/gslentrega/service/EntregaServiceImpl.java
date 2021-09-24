package com.dev.gslentrega.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.repositories.EntregaRepository;

@Service
public class EntregaServiceImpl implements EntregaService {

	@Autowired
	private EntregaRepository repository;
	
	@Override
	public List<Entrega> buscarEntregas() {
		return repository.findAll();
	}

	@Override
	public Entrega buscarEntregaById(Long id) {
		return repository.findById(id).get();
	}

}
