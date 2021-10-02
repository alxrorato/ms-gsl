package com.dev.gslentrega.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dev.gslentrega.response.Cliente;

@Component
@FeignClient(name = "gsl-cliente", path = "/clientes")
public interface ClienteFeignClient {

	@GetMapping(path = "buscarPorCnpj/{cnpj}")
	public ResponseEntity<Cliente> buscarClientesByCnpj(@PathVariable Long cnpj);

}
