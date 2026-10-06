package com.biblioteca.gp5.passwordresettoken.util;

import java.security.SecureRandom;
import java.util.HexFormat;

public final class TokenGenerator {
	
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	
	private TokenGenerator() {}
	
	public static String generate() {
		byte[] bytes = new byte[32];
		
		SECURE_RANDOM.nextBytes(bytes);
		
		String token = HexFormat.of().formatHex(bytes);
		
		return token;
	}

}
