package com.biblioteca.gp5.exception.category;

public class CategoryAlreadCadastredException extends RuntimeException {

	public CategoryAlreadCadastredException(String message) {
		super(message);
	}
	
	public CategoryAlreadCadastredException(String message, Throwable cause) {
		super(message, cause);
	}
}
