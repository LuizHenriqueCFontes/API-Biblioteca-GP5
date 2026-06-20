package com.biblioteca.gp5.exception.storage;

public class FailedSaveFileException extends RuntimeException {
	
	public FailedSaveFileException(String message) {
		super(message);
	}
	
	public FailedSaveFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
