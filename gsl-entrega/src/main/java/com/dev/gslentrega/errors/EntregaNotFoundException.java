package com.dev.gslentrega.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntregaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3781508933823595331L;

	public EntregaNotFoundException(String message) {
		super(message);
	}

	
}
