package com.biblioteca.gp5.user.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.gp5.exception.user.InvalidPasswordException;
import com.biblioteca.gp5.exception.user.InvalidRoleException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.user.dto.request.UpdatePasswordDTO;
import com.biblioteca.gp5.user.dto.request.UpdateRoleDTO;
import com.biblioteca.gp5.user.dto.request.UpdateUserDTO;
import com.biblioteca.gp5.user.dto.response.ListResponseDTO;
import com.biblioteca.gp5.user.dto.response.UpdateUserResponseDTO;
import com.biblioteca.gp5.user.mapper.UserMapper;
import com.biblioteca.gp5.user.model.Users;
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
	public UpdateUserResponseDTO updateUser(String id, UpdateUserDTO data) {
		// Tento carregar pelo id, caso apresente erro, lanço uma exception
		Users user = userRepository.findById(id)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		//Irei verificar os campos que foram alterados, devido ser um metodo patch
		if(data.username() != null && !data.username().isBlank()) {
			user.setUsername(data.username());
		}
		
		if(data.email() != null && !data.email().isBlank()) {
			user.setEmail(data.email());
		}
		
		if(data.phone() != null && !data.phone().isBlank()) {
			user.setPhone(data.phone());
		}
		
		//Irei salvar o novo usuário no repository
		userRepository.save(user);
		
		//Irei criar uma resposta com os dados e enviando o id do usuario
		UpdateUserResponseDTO response = new UpdateUserResponseDTO(id, user.getUsername(), user.getEmail(), user.getPhone());
		
		return response;
	}
	
	public Page<ListResponseDTO> listUsers(String username, Pageable pageable){
		Page<Users> user;
		
		if(username == null || username.isBlank()) {
			user = userRepository.findAll(pageable);
			
		}else {
			user = userRepository.findByUsernameContainingIgnoreCase(username, pageable);
		}
		
		// Converte a Page<Users> em Page<ListResponseDTO> utilizando o método map.
		// O mapper é aplicado em cada elemento da página, mantendo a paginação original.
		// Equivalente a uma lambda: user -> userMapper.toListResponseDTO(user)
		Page<ListResponseDTO> users = user.map(userMapper::toListResponseDTO);
		
		return users;
	}
	
	@Transactional
	public void updateRole(String id, UpdateRoleDTO data) {
		Users user = userRepository.findById(id)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		if(data.role() == user.getRole()) {
			throw new InvalidRoleException("Usuário ja possui essa role");
		}
		
		if(data.role() == null) {
			throw new InvalidRoleException("Role inválida");
		}
		
		
		user.setRole(data.role());
		
		//Salvo o usuario com a nova role
		userRepository.save(user);
		
	}
	
	@Transactional
	public void updatePassword(String id, UpdatePasswordDTO data) {
		Users user = userRepository.findById(id)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		passwordValidator.validate(data);
		
		if(!passwordEncoder.matches(data.oldPassword(), user.getPassword())) {
			throw new InvalidPasswordException("Senha inválida");	
		}
		
		if(passwordEncoder.matches(data.confirmNewPassword(), user.getPassword())) {
			throw new InvalidPasswordException("Senha não pode ser igual a anterior");
		}
		
		String passwordEncoded = passwordEncoder.encode(data.confirmNewPassword());
		
		// política de segurança: senha sempre armazenada com hash BCrypt
		user.setPassword(passwordEncoded);
		
		userRepository.save(user);
	}
	
	

}
