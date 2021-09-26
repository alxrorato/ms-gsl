package com.dev.gslcliente.errors;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6789005303298983973L;

	public ClienteNotFoundException(String message) {
		super(message);
	}

}
