package com.biblioteca.gp5.passwordresettoken.scheduler;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.biblioteca.gp5.passwordresettoken.service.PasswordResetTokenService;

@Component
public class DeleteTokenScheduler {
	
	private final PasswordResetTokenService passwordResetTokenService;
	
	public DeleteTokenScheduler(PasswordResetTokenService passwordResetTokenService) {
		this.passwordResetTokenService = passwordResetTokenService;
	}
	
	@Scheduled(fixedDelay = 15, timeUnit = TimeUnit.MINUTES)
	public void deleteToken() {
		passwordResetTokenService.deleteExpiredTokens();
	}

}
