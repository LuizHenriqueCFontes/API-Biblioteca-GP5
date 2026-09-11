package com.biblioteca.gp5.exception.passwordresettoken;

public class PasswordResetTokenExpiredException extends RuntimeException {
	public PasswordResetTokenExpiredException(String message) {
		super(message);
	}
	
	public PasswordResetTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}
}
