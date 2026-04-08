package com.biblioteca.gp5.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.security.token.TokenService;
import com.biblioteca.gp5.user.model.Users;
import com.biblioteca.gp5.user.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {
	
	private final TokenService tokenService;
	private final UserRepository userRepository;
	
	public AuthFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			
			return;
		}

		String token = authHeader.substring(7);
		String subject = tokenService.extractSubject(token);
	
		if(subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			Users user = userRepository.findById(subject)
										.orElseThrow(() -> new UserNotFoundException("Usuáro não encontrado"));
			
			if(tokenService.validateToken(token)) {
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
	}

}
