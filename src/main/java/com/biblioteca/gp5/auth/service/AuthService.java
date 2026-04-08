package com.biblioteca.gp5.auth.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.auth.dto.request.LoginDTO;
import com.biblioteca.gp5.auth.dto.request.RegisterDTO;
import com.biblioteca.gp5.auth.dto.response.AuthResponseDTO;
import com.biblioteca.gp5.exception.user.EmailAlreadyExistsException;
import com.biblioteca.gp5.security.token.TokenService;
import com.biblioteca.gp5.user.model.UserRole;
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
		
		// Verifica se o e-mail já está cadastrado no banco; lança exceção caso exista
		if(userRepository.existsByEmail(data.email())) {
			throw new EmailAlreadyExistsException("Email já está cadastrado");
			
		}
		
		// Criptografa a senha usando o PasswordEncoder do Spring Security
		String senhaCriptografada = passwordEncoder.encode(data.password());
		
		UserRole role;
		
		// Define a role do usuário com base no domínio do e-mail
		if(data.email().endsWith("@edu.br")) {
			role = UserRole.ALUNO;
			
		}else {
			role = UserRole.USER;
		}
		
		// Cria a entidade Users com os dados fornecidos e a role definida
		Users user = new Users(data.username(), data.email(), data.phone(), senhaCriptografada, role);
		
		// Salva o usuário no banco de dados
		userRepository.save(user);
		
		// Gera um token JWT para autenticação do usuário recém-criado
		String token = tokenService.generateToken(user);
		
		// Cria e retorna o DTO de resposta com o token e o username
		AuthResponseDTO response = new AuthResponseDTO(token, data.username());
		
		return response;
		
	}
}
