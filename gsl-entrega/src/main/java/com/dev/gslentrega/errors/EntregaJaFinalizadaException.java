package com.dev.gslentrega.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntregaJaFinalizadaException extends RuntimeException {

	private static final long serialVersionUID = -1385860418730355807L;

	public EntregaJaFinalizadaException(String message) {
		super(message);
	}

	
}
