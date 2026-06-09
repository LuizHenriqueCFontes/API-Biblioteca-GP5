package com.biblioteca.gp5.exception.category;

public class CategoryInUseException extends RuntimeException {
	
	public CategoryInUseException(String message) {
		super(message);
	}
	
	public CategoryInUseException(String message, Throwable cause) {
		super(message, cause);
	}
}
