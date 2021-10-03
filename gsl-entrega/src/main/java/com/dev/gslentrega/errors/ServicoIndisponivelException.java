package com.dev.gslentrega.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServicoIndisponivelException extends RuntimeException {

	private static final long serialVersionUID = 5872607754207583231L;

	public ServicoIndisponivelException(String message) {
		super(message);
	}
	
}
