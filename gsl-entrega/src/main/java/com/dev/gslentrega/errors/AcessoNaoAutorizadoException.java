package com.dev.gslentrega.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AcessoNaoAutorizadoException extends RuntimeException {

	private static final long serialVersionUID = -3318424889311640955L;

	public AcessoNaoAutorizadoException(String message) {
		super(message);
	}

	
}
