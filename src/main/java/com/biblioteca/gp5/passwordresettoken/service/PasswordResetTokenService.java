package com.biblioteca.gp5.passwordresettoken.service;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.email.service.EmailService;
import com.biblioteca.gp5.passwordresettoken.repository.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenService {
	
	private final PasswordResetTokenRepository passwordResetTokenRepository;
	private final EmailService emailService;
	
	public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository, EmailService emailService) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
		this.emailService = emailService;
	}

}
