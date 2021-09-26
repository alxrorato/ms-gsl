package com.dev.gslcliente.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.gslcliente.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>  {
	
	List<Cliente> findByNomeComercial(String nomeComercial);

	List<Cliente> findByNomeComercialIgnoreCaseContaining(String nomeComercial);

}
