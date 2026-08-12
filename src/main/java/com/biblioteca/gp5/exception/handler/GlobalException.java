package com.biblioteca.gp5.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.biblioteca.gp5.exception.auth.EmailAlreadyExistsException;
import com.biblioteca.gp5.exception.auth.PasswordMatches;
import com.biblioteca.gp5.exception.book.BookAlreadyRegisteredException;
import com.biblioteca.gp5.exception.book.BookNotAvailableException;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.exception.category.CategoryAlreadCadastredException;
import com.biblioteca.gp5.exception.category.CategoryInUseException;
import com.biblioteca.gp5.exception.category.CategoryNotFoundException;
import com.biblioteca.gp5.exception.dto.ErrorResponse;
import com.biblioteca.gp5.exception.loan.LoanNotFoundException;
import com.biblioteca.gp5.exception.loan.UserHasLoanException;
import com.biblioteca.gp5.exception.reading.ReadingAlreadyStartedException;
import com.biblioteca.gp5.exception.reading.ReadingNotFoundException;
import com.biblioteca.gp5.exception.security.TokenCreationException;
import com.biblioteca.gp5.exception.security.TokenExpiredAuthenticationException;
import com.biblioteca.gp5.exception.security.TokenValidationException;
import com.biblioteca.gp5.exception.storage.FailedCreateDirectoryException;
import com.biblioteca.gp5.exception.storage.FailedSaveFileException;
import com.biblioteca.gp5.exception.user.InvalidPasswordException;
import com.biblioteca.gp5.exception.user.InvalidRoleException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;

@RestControllerAdvice // Essa anotação significa uma classe de erro global
public class GlobalException {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Usuário não encontrado", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Usuário ou senha incorreto", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUsuarioNaoEncontradoException(UserNotFoundException ex) {

		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Usuário não encontrado", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Senha inválida",
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(InvalidRoleException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRoleException(InvalidRoleException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Role inválida",
				ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"O email já está cadastrado", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(PasswordMatches.class)
	public ResponseEntity<ErrorResponse> handlePasswordMatches(PasswordMatches ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "As senhas não estão iguais", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}


	@ExceptionHandler(TokenCreationException.class)
	public ResponseEntity<Object> handleTokenCreationException(TokenCreationException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
				"Falha ao criar token", ex.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), 
				"Token expirado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(TokenValidationException.class)
	public ResponseEntity<ErrorResponse> handleTokenValidationException(TokenValidationException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
				"Falha ao validar token", ex.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(TokenExpiredAuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleTokenExpiredAuthenticationException(TokenExpiredAuthenticationException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
												"Token expirado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Livro não encontrado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BookAlreadyRegisteredException.class)
	public ResponseEntity<ErrorResponse> handleBookAlreadyRegistedException(BookAlreadyRegisteredException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Livro já está cadastrado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BookNotAvailableException.class)
	public ResponseEntity<ErrorResponse> handleBookNotAvailableException(BookNotAvailableException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Livro não disponível", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	
	@ExceptionHandler(UserHasLoanException.class)
	public ResponseEntity<ErrorResponse> handleUserHasLoanException(UserHasLoanException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Usuário ja possui o emprestimo do livro", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleLoanNotFoundException(LoanNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Empréstimo não encontrado", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(CategoryAlreadCadastredException.class)
	public ResponseEntity<ErrorResponse> handleCategoryAlreadCadastedException(CategoryAlreadCadastredException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Categoria já cadastrada", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Categoria não encontrada", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(CategoryInUseException.class)
	public ResponseEntity<ErrorResponse> handlerCategoryInUseException(CategoryInUseException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Categoria em uso", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(ReadingAlreadyStartedException.class)
	public ResponseEntity<ErrorResponse> handlerReadingAlreadyStartedException(ReadingAlreadyStartedException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
					"Leitura iniciada", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);	
	}
	
	@ExceptionHandler(ReadingNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerReadingNotFoundException(ReadingNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
						"Leitura não encontrada", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(FailedCreateDirectoryException.class)
	public ResponseEntity<ErrorResponse> handlerFailedCreatedDirectoryException(FailedCreateDirectoryException ex){
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Falha ao criar diretorios", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(FailedSaveFileException.class)
	public ResponseEntity<ErrorResponse> handlerFailedSaveFileException(FailedSaveFileException ex) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
						"Falha ao salvar arquivo", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValid(MethodArgumentNotValidException ex) {
		
		String message = ex.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
		
		
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Falha ao validar os dados", message);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
