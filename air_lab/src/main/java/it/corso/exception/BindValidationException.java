package it.corso.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.corso.helper.PasswordValidationException;
import it.corso.helper.Response;

@ControllerAdvice
public class BindValidationException {
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<Map<String, String>>handleExceptionValidation(BindException e){
		Map<String, String> validationErrors = new HashMap<>();
		e.getBindingResult()
		.getFieldErrors()
		.forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(validationErrors);
	}
	
	@ExceptionHandler(PasswordValidationException.class)
	public ResponseEntity<Response> passwordChecker( PasswordValidationException e){
		Response risposta = new Response(400, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(risposta);
	}
		
	
}
