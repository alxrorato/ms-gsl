package com.dev.gslentrega.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class OperacaoNaoEfetuadaException extends RuntimeException {

	private static final long serialVersionUID = -6622220318380175091L;

	public OperacaoNaoEfetuadaException(String message) {
		super(message);
	}

	
}
