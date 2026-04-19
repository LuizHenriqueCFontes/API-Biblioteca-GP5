package com.biblioteca.gp5.auth.validation;

import org.springframework.stereotype.Component;

import com.biblioteca.gp5.exception.auth.EmailAlreadyExistsException;
import com.biblioteca.gp5.user.repository.UserRepository;

@Component
public class RegisterValidator {
	
	private final UserRepository userRepository;
	
	public RegisterValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void validateEmail(String email) {
		// Verifica se o e-mail já está cadastrado no banco; lança exceção caso exista
		if(userRepository.existsByEmail(email)) {
			throw new EmailAlreadyExistsException("Email já está cadastrado");
					
		}
	}
}
