package com.biblioteca.gp5.security.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration //Criei uma classe de configuration
public class JwtConfig {
	
	// Secret usado para assinar o JWT
	@Value("${api.security.token}")
	private String secret;
	
	// Bean que define o algoritmo de assinatura do token
	@Bean
	Algorithm jwtAlgorithm() {
		return Algorithm.HMAC256(secret);
	}
}
