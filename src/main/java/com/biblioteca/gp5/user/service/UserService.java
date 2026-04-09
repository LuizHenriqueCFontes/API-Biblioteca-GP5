package com.biblioteca.gp5.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.user.dto.request.UpdateRoleDTO;
import com.biblioteca.gp5.user.dto.request.UpdateUserDTO;
import com.biblioteca.gp5.user.dto.response.UpdateUserResponseDTO;
import com.biblioteca.gp5.user.model.Users;
import com.biblioteca.gp5.user.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public UpdateUserResponseDTO updateUser(String id, UpdateUserDTO data) {
		// Tento carregar pelo id, caso apresente erro, lanço uma exception
		Users user = userRepository.findById(id)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		//Irei verificar os campos que foram alterados, devido ser um metodo patch
		if(data.username() != null && data.username().isBlank()) {
			user.setUsername(data.username());
		}
		
		if(data.email() != null && data.email().isBlank()) {
			user.setEmail(data.email());
		}
		
		if(data.phone() != null && data.phone().isBlank()) {
			user.setPhone(data.phone());
		}
		
		//Irei salvar o novo usuário no repository
		userRepository.save(user);
		
		//Irei criar uma resposta com os dados e enviando o id do usuario
		UpdateUserResponseDTO response = new UpdateUserResponseDTO(id, user.getUsername(), user.getEmail(), user.getPhone());
		
		return response;
	}
	
	public List<List>
	
	public void updateRole(String id, UpdateRoleDTO data) {
		Users user = userRepository.findById(id)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		//Irei verificar se a role veio vazia e se a role enviada for diferente da
		if(data.role() != null && user.getRole() != data.role()) {
			user.setRole(data.role());
		}
		
		//Salvo o usuario com a nova role
		userRepository.save(user);
		
	}
	
	

}
