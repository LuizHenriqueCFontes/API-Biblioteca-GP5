package com.biblioteca.gp5.security.token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.biblioteca.gp5.exception.security.TokenCreationException;
import com.biblioteca.gp5.exception.security.TokenValidationException;
import com.biblioteca.gp5.user.model.UserRole;
import com.biblioteca.gp5.user.model.Users;

@Service
public class TokenService {
	
	private final Algorithm algorithm;
	
	public TokenService(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	public String generateToken(Users user) {
		try {
			List<UserRole> role = List.of(user.getRole());
			
			String token = JWT.create()
								.withIssuer("api-biblioteca-gp5")
								.withSubject(user.getIdUsers())
								.withClaim("role", List.of(role))
								.withExpiresAt(expirationTime())
								.sign(algorithm);
			
			return token;
			
		}catch(JWTCreationException ex) {
			throw new TokenCreationException("Falha ao criar token", ex);
			
		}
		
	}
	
	public boolean validateToken(String token) {
		try {
			JWT.require(algorithm)
				.withIssuer("api-biblioteca-gp5")
				.build()
				.verify(token);
			
			return true;
			
			//Casp o metodo falhe, eu lanço uma exception
		}catch(JWTVerificationException ex){
			throw new TokenValidationException("Falha ao validar o token", ex);
			
		}
		
	}
	
	public String extractSubject(String token) {
		try {
			String subject = JWT.require(algorithm)
								.withIssuer("api-biblioteca-gp5")
								.build()
								.verify(token)
								.getSubject();
			
			return subject;
			
		}catch(JWTVerificationException ex) {
			throw new TokenValidationException("Token inválido ou expirado", ex); 
		}
	}
	
	public Instant expirationTime() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
