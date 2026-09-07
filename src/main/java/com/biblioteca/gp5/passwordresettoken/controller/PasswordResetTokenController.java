package com.biblioteca.gp5.passwordresettoken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.passwordresettoken.dto.ForgotPasswordRequestDTO;
import com.biblioteca.gp5.passwordresettoken.service.PasswordResetTokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/reset-password")
public class PasswordResetTokenController {
	
	private final PasswordResetTokenService passwordResetTokenService;
	
	public PasswordResetTokenController(PasswordResetTokenService passwordResetTokenService) {
		this.passwordResetTokenService = passwordResetTokenService;
	}
	
	@PostMapping
	public ResponseEntity<Void> requestPasswordReset(@RequestBody @Valid ForgotPasswordRequestDTO request) {
		passwordResetTokenService.requestPasswordReset(request);
		
		return ResponseEntity.noContent().build();
		
	}

}
