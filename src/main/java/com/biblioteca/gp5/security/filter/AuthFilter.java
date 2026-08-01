package com.biblioteca.gp5.security.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.biblioteca.gp5.exception.dto.ErrorResponse;
import com.biblioteca.gp5.exception.security.TokenExpiredAuthenticationException;
import com.biblioteca.gp5.exception.security.TokenValidationException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.security.token.TokenService;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class AuthFilter extends OncePerRequestFilter {
	
	private final TokenService tokenService;
	private final UserRepository userRepository;
	private final ObjectMapper objectMapper;
	
	public AuthFilter(TokenService tokenService, UserRepository userRepository, ObjectMapper objectMapper) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
		this.objectMapper = objectMapper; 
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			
			return;
		}

		try {
			
			String token = authHeader.substring(7);
			String subject = tokenService.extractSubject(token);
			
			UUID UuidSubject = UUID.fromString(subject);
		
			if(subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				User user = userRepository.findById(UuidSubject)
											.orElseThrow(() -> new UserNotFoundException("Usuáro não encontrado"));
				
				if(tokenService.verifyTokenExpired(token)) {
						
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
				
			}
			
			filterChain.doFilter(request, response);
			
		}catch(TokenExpiredAuthenticationException ex) {
			
			writeErrorResponse(response, HttpStatus.UNAUTHORIZED, ex.getMessage());
			return;
			
		}catch(TokenValidationException ex) {
			writeErrorResponse(response, HttpStatus.UNAUTHORIZED, ex.getMessage());
			return;
		}
	}
	
	private void writeErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException{
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message);
		
		response.setStatus(status.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		objectMapper.writeValue(response.getWriter(), error);
	}

}
