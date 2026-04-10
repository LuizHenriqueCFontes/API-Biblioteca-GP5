package com.biblioteca.gp5.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.user.dto.response.ListResponseDTO;
import com.biblioteca.gp5.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<Page<ListResponseDTO>> listUsers(@RequestParam(required = false) String username, Pageable pageablwe){
		Page<ListResponseDTO> users = userService.listUsers(username, pageablwe);
		
		return ResponseEntity.ok(users);
		
	}

}
