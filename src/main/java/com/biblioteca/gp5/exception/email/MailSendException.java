package com.biblioteca.gp5.exception.email;

public class MailSendException extends RuntimeException {
	public MailSendException(String message, Throwable cause) {
		super(message, cause);
	}
}
