package com.biblioteca.gp5.exception.user;

public class InvalidRoleException extends RuntimeException {
	
	public InvalidRoleException(String message) {
		super(message);
	}
	
	public InvalidRoleException(String message, Throwable cause) {
		super(message, cause);
	}
}
