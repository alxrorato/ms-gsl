package com.dev.gslcliente.service.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.gslcliente.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>  {
	
	List<Cliente> findByCnpj(Long cnpj);
	
	List<Cliente> findByNomeComercial(String nomeComercial);

	List<Cliente> findByNomeComercialIgnoreCaseContaining(String nomeComercial);

}
