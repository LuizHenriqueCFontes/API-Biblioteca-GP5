package com.biblioteca.gp5.exception.auth;

public class PasswordMatches extends RuntimeException {
	
	public PasswordMatches(String message) {
		super(message);
	}
	
	public PasswordMatches(String message, Throwable cause) {
		super(message, cause);
	}
}
