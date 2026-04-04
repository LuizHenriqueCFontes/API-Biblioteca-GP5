package com.biblioteca.gp5.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.gp5.exception.dto.ErrorResponse;
import com.biblioteca.gp5.exception.security.TokenCreationException;
import com.biblioteca.gp5.exception.security.TokenValidationException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;

@RestControllerAdvice //Essa anotação significa uma classe erro global
public class GlobalException {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handlerUsuarioNaoEncontradoException(UserNotFoundException ex){
		
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Usuário não encontrado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(TokenCreationException.class)
	public ResponseEntity<Object> handlerTokenCreationException(TokenCreationException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Falha ao criar token", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(TokenValidationException.class)
	public ResponseEntity<Object> handlerTokenValidationException(TokenValidationException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Falha ao validar token", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}


	
