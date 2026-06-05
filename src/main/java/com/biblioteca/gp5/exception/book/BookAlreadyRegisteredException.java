package com.biblioteca.gp5.exception.book;

public class BookAlreadyRegisteredException extends RuntimeException {
	
	public BookAlreadyRegisteredException(String message) {
		super(message);
	}
	
	public BookAlreadyRegisteredException(String message, Throwable cause) {
		super(message, cause);
	}
}
