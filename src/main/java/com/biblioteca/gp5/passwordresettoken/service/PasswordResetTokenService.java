 package com.biblioteca.gp5.passwordresettoken.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.email.service.EmailService;
import com.biblioteca.gp5.passwordresettoken.model.PasswordResetToken;
import com.biblioteca.gp5.passwordresettoken.repository.PasswordResetTokenRepository;
import com.biblioteca.gp5.passwordresettoken.util.TokenGenerator;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;

@Service
public class PasswordResetTokenService {
	
	private final PasswordResetTokenRepository passwordResetTokenRepository;
	private final EmailService emailService;
	private final UserRepository userRepository;
	
	public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository, EmailService emailService, UserRepository userRepository) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
		this.emailService = emailService;
		this.userRepository = userRepository;
	}
	
	public void requestPasswordReset(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if(user.isEmpty()) {
			return;
		}
		
		String token = TokenGenerator.generate();
		
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, user.get(), LocalDateTime.now().plusMinutes(15));
		
		passwordResetTokenRepository.save(passwordResetToken);
		
	}

}
