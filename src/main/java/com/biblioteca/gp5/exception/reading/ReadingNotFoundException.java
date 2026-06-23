package com.biblioteca.gp5.exception.reading;

public class ReadingNotFoundException extends RuntimeException {
	
	public ReadingNotFoundException(String message) {
		super(message);
	}
	
	public ReadingNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
