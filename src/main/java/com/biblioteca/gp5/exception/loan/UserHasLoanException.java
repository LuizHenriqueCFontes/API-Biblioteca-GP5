package com.biblioteca.gp5.exception.loan;

public class UserHasLoanException extends RuntimeException{
	
	public UserHasLoanException(String message) {
		super(message);
	}
	
	public UserHasLoanException(String message, Throwable cause) {
		super(message, cause);
	}
}
