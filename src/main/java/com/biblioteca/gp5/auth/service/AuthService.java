package com.biblioteca.gp5.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.auth.dto.request.LoginDTO;
import com.biblioteca.gp5.auth.dto.request.RegisterDTO;
import com.biblioteca.gp5.auth.dto.response.AuthResponseDTO;
import com.biblioteca.gp5.security.token.TokenService;
import com.biblioteca.gp5.user.model.Users;
import com.biblioteca.gp5.user.repository.UserRepository;

@Service
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	private final UserRepository userRepository;
	
	public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, TokenService tokenService, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
		this.userRepository = userRepository;
		
	}
	
	public AuthResponseDTO login(LoginDTO data) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		
		Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		Users user = (Users) auth.getPrincipal();
		
		String token = tokenService.generateToken(user);
		
		AuthResponseDTO response = new AuthResponseDTO(token, user.getUsername());
		
		return response;
	}
	
	public AuthResponseDTO register(RegisterDTO data) {
		if(userRepository.findByEmail(data.email())) {
			throw new 
		}
		
	}
}
