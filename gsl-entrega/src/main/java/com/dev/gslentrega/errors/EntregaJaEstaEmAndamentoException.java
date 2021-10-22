package com.dev.gslentrega.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntregaJaEstaEmAndamentoException extends RuntimeException {

	private static final long serialVersionUID = -5254753915743385220L;

	public EntregaJaEstaEmAndamentoException(String message) {
		super(message);
	}

	
}
