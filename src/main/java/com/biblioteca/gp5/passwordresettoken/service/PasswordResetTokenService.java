 package com.biblioteca.gp5.passwordresettoken.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.biblioteca.gp5.email.service.EmailService;
import com.biblioteca.gp5.passwordresettoken.dto.ForgotPasswordRequestDTO;
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
	private final SpringTemplateEngine templateEngine;
	
	public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository, EmailService emailService, UserRepository userRepository,
			SpringTemplateEngine templateEngine) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
		this.emailService = emailService;
		this.userRepository = userRepository;
		this.templateEngine = templateEngine;
	}
	
	public void requestPasswordReset(ForgotPasswordRequestDTO request) {
		Optional<User> user = userRepository.findByEmail(request.email());
		
		if(user.isEmpty()) {
			return;
		}
		
		String token = TokenGenerator.generate();
		
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, user.get(), LocalDateTime.now().plusMinutes(15));
		
		passwordResetTokenRepository.save(passwordResetToken);
		
		String resetUrl = "http://localhost:5153/reset/password?token=" + token;
		
		Context context = new Context();
		
		context.setVariable("resetUrl", resetUrl);
		
		
		String html = templateEngine.process("password-reset", context);
		
		emailService.sendEmail(request.email(), "Redefinir senha", html);
		
		
	}

}
