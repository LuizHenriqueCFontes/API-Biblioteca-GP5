package com.biblioteca.gp5.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.gp5.exception.auth.EmailAlreadyExistsException;
import com.biblioteca.gp5.exception.auth.PasswordMatches;
import com.biblioteca.gp5.exception.book.BookAlreadyRegisteredException;
import com.biblioteca.gp5.exception.book.BookNotAvailableException;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.exception.category.CategoryAlreadCadastredException;
import com.biblioteca.gp5.exception.category.CategoryNotFoundException;
import com.biblioteca.gp5.exception.dto.ErrorResponse;
import com.biblioteca.gp5.exception.loan.LoanNotFoundException;
import com.biblioteca.gp5.exception.loan.UserHasLoanException;
import com.biblioteca.gp5.exception.security.TokenCreationException;
import com.biblioteca.gp5.exception.security.TokenValidationException;
import com.biblioteca.gp5.exception.user.InvalidPasswordException;
import com.biblioteca.gp5.exception.user.InvalidRoleException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;

@RestControllerAdvice // Essa anotação significa uma classe de erro global
public class GlobalException {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handlerUsernameNotFoundException(UsernameNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Usuário não encontrado", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handlerBadCredentialsException(BadCredentialsException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Usuário ou senha incorreto", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handlerUsuarioNaoEncontradoException(UserNotFoundException ex) {

		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Usuário não encontrado", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<Object> handlerInvalidPasswordException(InvalidPasswordException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Senha inválida",
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(InvalidRoleException.class)
	public ResponseEntity<Object> handlerInvalidRoleException(InvalidRoleException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Role inválida",
				ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handlerEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"O email já está cadastrado", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(PasswordMatches.class)
	public ResponseEntity<Object> handlerPasswordMatches(PasswordMatches ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "As senhas não estão iguais", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}


	@ExceptionHandler(TokenCreationException.class)
	public ResponseEntity<Object> handlerTokenCreationException(TokenCreationException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Falha ao criar token", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(TokenValidationException.class)
	public ResponseEntity<Object> handlerTokenValidationException(TokenValidationException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Falha ao validar token", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<Object> handlerBookNotFoundException(BookNotFoundException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Livro não encontrado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BookAlreadyRegisteredException.class)
	public ResponseEntity<Object> handlerBookAlreadyRegistedException(BookAlreadyRegisteredException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Livro já está cadastrado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BookNotAvailableException.class)
	public ResponseEntity<Object> handlerBookNotAvailableException(BookNotAvailableException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Livro não disponível", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	
	@ExceptionHandler(UserHasLoanException.class)
	public ResponseEntity<Object> handlerUserHasLoanException(UserHasLoanException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Usuário ja possui o emprestimo do livro", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<Object> handlerLoanNotFoundException(LoanNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Empréstimo não encontrado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(CategoryAlreadCadastredException.class)
	public ResponseEntity<Object> handlerCategoryAlreadCadastedException(CategoryAlreadCadastredException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Categoria já cadastrada", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<Object> handlerCategoryNotFoundException(CategoryNotFoundException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Categoria não encontrada", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
