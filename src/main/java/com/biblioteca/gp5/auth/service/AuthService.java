package com.biblioteca.gp5.auth.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.auth.dto.request.LoginDTO;
import com.biblioteca.gp5.auth.dto.request.RegisterDTO;
import com.biblioteca.gp5.auth.dto.request.RegisterValidateDTO;
import com.biblioteca.gp5.auth.dto.response.AuthResponseDTO;
import com.biblioteca.gp5.auth.validation.RegisterValidator;
import com.biblioteca.gp5.exception.auth.EmailAlreadyExistsException;
import com.biblioteca.gp5.exception.auth.PasswordMatches;
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
	private final RegisterValidator registerValidator;

	public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			TokenService tokenService, UserRepository userRepository, RegisterValidator registerValidator) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
		this.userRepository = userRepository;
		this.registerValidator = registerValidator;
	}

	public AuthResponseDTO login(LoginDTO data) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				data.email(), data.password());

		Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		Users user = (Users) auth.getPrincipal();

		String token = tokenService.generateToken(user);

		AuthResponseDTO response = new AuthResponseDTO(token, user.getUsername());

		return response;
	}

	public void registerValidate(RegisterValidateDTO data) {
		registerValidator.validateEmail(data.email());
		
	}

	public AuthResponseDTO register(RegisterDTO data) {
		
		registerValidator.validateEmail(data.email());
		

		if (!data.password().equals(data.confirmPassword())) {
			throw new PasswordMatches("As senhas não estão iguais");
		}

		String senhaCriptografada = passwordEncoder.encode(data.password());

		UserRole role;

		// Define a role do usuário com base no domínio do e-mail
		if (data.email().endsWith("@edu.df.senac.br")) {
			role = UserRole.ALUNO;

		} else {
			role = UserRole.USER;
		}

		// Cria a entidade Users com os dados fornecidos e a role definida
		Users user = new Users(data.username(), data.email(), data.phone(), senhaCriptografada, role);

		user.setEnabled(true);

		// Salva o usuário no banco de dados
		userRepository.save(user);

		// Gera um token JWT para autenticação do usuário recém-criado
		String token = tokenService.generateToken(user);

		// Cria e retorna o DTO de resposta com o token e o username
		AuthResponseDTO response = new AuthResponseDTO(token, data.username());

		return response;

	}
}
