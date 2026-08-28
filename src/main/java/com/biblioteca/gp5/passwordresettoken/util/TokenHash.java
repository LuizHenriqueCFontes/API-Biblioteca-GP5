package com.biblioteca.gp5.passwordresettoken.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public final class TokenHash {
	
	private TokenHash() {}
	
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
	
	public static boolean matches(String token, String storedHash) {
		
		String tokenHash = hash(token);
		
		boolean tokenIsValid = tokenHash.equals(storedHash);
		
		return tokenIsValid;
	}

}
