package com.biblioteca.gp5.exception.security;

public class TokenExpiredAuthenticationException extends RuntimeException {
	
	public TokenExpiredAuthenticationException(String message) {
		super(message);
	}
	
	public TokenExpiredAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
