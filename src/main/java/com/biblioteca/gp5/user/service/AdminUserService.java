package com.biblioteca.gp5.user.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.gp5.exception.user.InvalidRoleException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.user.dto.request.UpdateRoleRequestDTO;
import com.biblioteca.gp5.user.dto.response.UserListResponseDTO;
import com.biblioteca.gp5.user.mapper.UserMapper;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;

@Service
public class AdminUserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public AdminUserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	
	@Transactional
	public void updateRole(UUID id, UpdateRoleRequestDTO request) {
		User user = userRepository.findById(id)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		if(request.role() == user.getRole()) {
			throw new InvalidRoleException("Usuário ja possui essa role");
		}
		
		if(request.role() == null) {
			throw new InvalidRoleException("Role inválida");
		}
		
		
		user.setRole(request.role());
		
		//Salvo o usuario com a nova role
		userRepository.save(user);	
	}
	
	public Page<UserListResponseDTO> listUsers(String username, Pageable pageable){
		Page<User> user;
		
		if(username == null || username.isBlank()) {
			user = userRepository.findAll(pageable);
			
		}else {
			user = userRepository.findByUsernameContainingIgnoreCase(username, pageable);
		}
		
		// Converte a Page<Users> em Page<ListResponseDTO> utilizando o método map.
		// O mapper é aplicado em cada elemento da página, mantendo a paginação original.
		// Equivalente a uma lambda: user -> userMapper.toListResponseDTO(user)
		Page<UserListResponseDTO> users = user.map(userMapper::toUserListResponseDTO);
		
		return users;
	}
}
