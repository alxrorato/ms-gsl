package com.dev.gslcliente.errors;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteInvalidIdException extends RuntimeException {

	private static final long serialVersionUID = 1026174848746998253L;

	public ClienteInvalidIdException(String message) {
		super(message);
	}

}
