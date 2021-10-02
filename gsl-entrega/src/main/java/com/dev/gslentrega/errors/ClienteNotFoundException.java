package com.dev.gslentrega.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4284306490200932630L;

	public ClienteNotFoundException(String message) {
		super(message);
	}

	
}
