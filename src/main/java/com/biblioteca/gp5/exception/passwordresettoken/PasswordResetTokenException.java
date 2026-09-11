package com.biblioteca.gp5.exception.passwordresettoken;

public class PasswordResetTokenException extends RuntimeException {
	
	public PasswordResetTokenException(String message) {
		super(message);
	}
	
	public PasswordResetTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
