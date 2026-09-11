 package com.biblioteca.gp5.passwordresettoken.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.biblioteca.gp5.email.service.EmailService;
import com.biblioteca.gp5.passwordresettoken.dto.ForgotPasswordRequestDTO;
import com.biblioteca.gp5.passwordresettoken.dto.ResetPasswordRequestDTO;
import com.biblioteca.gp5.passwordresettoken.model.PasswordResetToken;
import com.biblioteca.gp5.passwordresettoken.repository.PasswordResetTokenRepository;
import com.biblioteca.gp5.passwordresettoken.util.TokenGenerator;
import com.biblioteca.gp5.passwordresettoken.util.TokenHash;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;
import com.biblioteca.gp5.user.validator.PasswordValidator;

import jakarta.transaction.Transactional;

@Service
public class PasswordResetTokenService {
	
	private final PasswordResetTokenRepository passwordResetTokenRepository;
	private final EmailService emailService;
	private final UserRepository userRepository;
	private final SpringTemplateEngine templateEngine;
	private final TokenHash tokenHash;
	private final PasswordValidator passwordValidator;
	
	public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository, EmailService emailService, UserRepository userRepository,
			SpringTemplateEngine templateEngine, TokenHash tokenHash, PasswordValidator passwordValidator) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
		this.emailService = emailService;
		this.userRepository = userRepository;
		this.templateEngine = templateEngine;
		this.tokenHash = tokenHash;
		this.passwordValidator = passwordValidator;
	}
	
	public void requestPasswordReset(ForgotPasswordRequestDTO request) {
		Optional<User> user = userRepository.findByEmail(request.email());
		
		if(user.isEmpty()) {
			return;
		}
		
		String token = TokenGenerator.generate();
		
		String tokenHash = TokenHash.hash(token);
		
		PasswordResetToken passwordResetToken = new PasswordResetToken(tokenHash, user.get(), LocalDateTime.now().plusMinutes(15));
		
		passwordResetTokenRepository.save(passwordResetToken);
		
		String resetUrl = "http://localhost:5153/reset/password?token=" + token;
		
		Context context = new Context();
		
		context.setVariable("resetUrl", resetUrl);
		
		String html = templateEngine.process("password-reset", context);
		
		emailService.sendEmail(request.email(), "Redefinir senha", html);
	}
	
	public void resetPassword(ResetPasswordRequestDTO request) {
		
		PasswordResetToken passwordResetToken = tokenHash.findToken(request.token());
		
		User user = passwordResetToken.getUser();
		
		passwordValidator.validate(request.password(), request.confirmPassword());
		
		user.setPassword(request.password());
	}
	
	@Transactional
	public void deleteExpiredTokens() {
		passwordResetTokenRepository.deleteExpiredTokens(LocalDateTime.now());
	}

}
