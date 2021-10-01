package com.dev.gslcliente.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ClienteJaExisteException extends RuntimeException {

	private static final long serialVersionUID = -2671097059701301653L;

	public ClienteJaExisteException(String message) {
		super(message);
	}

}
