package com.biblioteca.gp5.passwordresettoken.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.biblioteca.gp5.exception.passwordresettoken.PasswordResetTokenException;
import com.biblioteca.gp5.exception.passwordresettoken.PasswordResetTokenExpiredException;
import com.biblioteca.gp5.passwordresettoken.model.PasswordResetToken;
import com.biblioteca.gp5.passwordresettoken.repository.PasswordResetTokenRepository;

@Component
public final class TokenHash {
	
	private final PasswordResetTokenRepository passwordResetTokenRepository;
	
	private TokenHash(PasswordResetTokenRepository passwordResetTokenRepository) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
	}
	
	public static String hash(String token) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			
			byte[] hash = messageDigest.digest(token.getBytes(StandardCharsets.UTF_8));
			
			String tokenSHA = HexFormat.of().formatHex(hash);
			
			return tokenSHA;
			
		} catch (NoSuchAlgorithmException e) {
			
			throw new IllegalStateException("Algoritmo SHA-256 não disponivel", e);
			
		}
	}
	
	public PasswordResetToken findToken(String token) {
		
		String tokenHash = hash(token);
		
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByTokenHash(tokenHash)
														.orElseThrow(() -> new PasswordResetTokenException("Token de recuperação inválido"));
		
		if(passwordResetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
			throw new PasswordResetTokenExpiredException("Token expirado");
		}
		
		return passwordResetToken;
	}

}
