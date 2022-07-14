package com.carlosetc.bookstore.exceptions;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> tratarObjectNotFoundException(ObjetoNaoEncontradoException e, ServletRequest rquest) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(System.currentTimeMillis(), status.value(), e.getMessage());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(ViolacaoIntegridadeDadosException.class)
	public ResponseEntity<StandardError> tratarDataIntegrityViolationException(ViolacaoIntegridadeDadosException e, ServletRequest rquest) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError(System.currentTimeMillis(), status.value(), e.getMessage());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> tratarMethodArgumentNotValidException(MethodArgumentNotValidException e, ServletRequest rquest) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError error = new ValidationError(System.currentTimeMillis(), status.value(), "Erro na validação dos campos");
		
		e.getBindingResult().getFieldErrors().forEach(fe -> error.addErrors(fe.getField(), fe.getDefaultMessage()));
		
		return ResponseEntity.status(status).body(error);
	}
	
}
