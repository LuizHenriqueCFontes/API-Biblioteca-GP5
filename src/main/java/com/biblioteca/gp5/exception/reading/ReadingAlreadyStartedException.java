package com.biblioteca.gp5.exception.reading;

public class ReadingAlreadyStartedException extends RuntimeException {
	
	public ReadingAlreadyStartedException(String message) {
		super(message);
	}
	
	public ReadingAlreadyStartedException(String message, Throwable cause) {
		super(message, cause);
	}
}
