package com.dev.gslentrega.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.gslentrega.entities.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Long>{

	Entrega findByCodigoSolicitacao(Long codigoSolicitacao);
}
