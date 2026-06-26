package com.biblioteca.gp5.exception.storage;

public class FailedCreateDirectoryException extends RuntimeException {
	
	public FailedCreateDirectoryException(String message) {
		super(message);
	}
	
	public FailedCreateDirectoryException(String message, Throwable cause) {
		super(message, cause);
	}
}
