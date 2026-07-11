package com.biblioteca.gp5.user.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.gp5.exception.user.InvalidPasswordException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.user.dto.request.UpdatePasswordRequestDTO;
import com.biblioteca.gp5.user.dto.request.UpdateUserRequestDTO;
import com.biblioteca.gp5.user.dto.response.UserResponseDTO;
import com.biblioteca.gp5.user.dto.response.UpdateUserResponseDTO;
import com.biblioteca.gp5.user.mapper.UserMapper;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;
import com.biblioteca.gp5.user.validator.PasswordValidator;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	private final PasswordValidator passwordValidator; 
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, PasswordValidator passwordValidator) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
		this.passwordValidator = passwordValidator;
	}
	
	@Transactional
	public UpdateUserResponseDTO updateUser(UUID id, UpdateUserRequestDTO request) {
		// Tento carregar pelo id, caso apresente erro, lanço uma exception
		User user = userRepository.findById(id)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		//Irei verificar os campos que foram alterados, devido ser um metodo patch
		if(request.username() != null && !request.username().isBlank()) {
			user.setUsername(request.username());
		}
		
		if(request.email() != null && !request.email().isBlank()) {
			user.setEmail(request.email());
		}
		
		if(request.phone() != null && !request.phone().isBlank()) {
			user.setPhone(request.phone());
		}
		
		//Irei salvar o novo usuário no repository
		userRepository.save(user);
		
		//Irei criar uma resposta com os dados e enviando o id do usuario
		UpdateUserResponseDTO response = new UpdateUserResponseDTO(id, user.getUsername(), user.getEmail(), user.getPhone());
		
		return response;
	}
	
	@Transactional
	public void updatePassword(UUID id, UpdatePasswordRequestDTO request) {
		User user = userRepository.findById(id)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		passwordValidator.validate(request);
		
		if(!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
			throw new InvalidPasswordException("Senha inválida");	
		}
		
		if(passwordEncoder.matches(request.confirmNewPassword(), user.getPassword())) {
			throw new InvalidPasswordException("Senha não pode ser igual a anterior");
		}
		
		String passwordEncoded = passwordEncoder.encode(request.confirmNewPassword());
		
		// política de segurança: senha sempre armazenada com hash BCrypt
		user.setPassword(passwordEncoded);
		
		userRepository.save(user);
	}
	
	public UserResponseDTO getUserData(UUID idUser) {
		User user = userRepository.findById(idUser)
								  .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		UserResponseDTO response = userMapper.toUserResponseDTO(user); 
		
		return response;
	}
	
	

}
