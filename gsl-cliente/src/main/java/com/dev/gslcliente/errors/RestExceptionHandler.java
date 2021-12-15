package com.dev.gslcliente.errors;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	// Tratamento para campos inválidos na requisição
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErrorModel> errors = getErrors(ex);		
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
	}

	private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status,
			List<ErrorModel> errors) {

		return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
	}

	private List<ErrorModel> getErrors(MethodArgumentNotValidException ex) {
	    return ex.getBindingResult().getFieldErrors().stream()
	            .map(error -> new ErrorModel(error.getDefaultMessage(), 
	            								error.getField(), 
	            								error.getRejectedValue()))
	            .collect(Collectors.toList());
	}	

	// Tratamento específico para cliente não encontrado quando pesquisado por um id inexistente
	@ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
    		ClienteNotFoundException ex, WebRequest request) {

        Map<String, Object> body = bodyResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	// Tratamento específico para quando for informado um id "0" na pesquisa de cliente por id
    @ExceptionHandler(ClienteInvalidIdException.class)
    public ResponseEntity<Object> handleResourceInvalidIdException(
    		ClienteInvalidIdException ex, WebRequest request) {

        Map<String, Object> body = bodyResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
	@ExceptionHandler(ClienteJaExisteException.class)
    public ResponseEntity<Object> handleResourceIntegrityException(
    		ClienteJaExisteException ex, WebRequest request) {

        Map<String, Object> body = bodyResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private Map<String, Object> bodyResponse(HttpStatus status, String message) {

    	Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.toString());
        body.put("message",message);
        
        return body;
    }
	
}
