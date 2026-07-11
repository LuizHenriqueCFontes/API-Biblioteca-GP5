package com.biblioteca.gp5.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.user.dto.request.UpdatePasswordRequestDTO;
import com.biblioteca.gp5.user.dto.request.UpdateUserRequestDTO;
import com.biblioteca.gp5.user.dto.response.UpdateUserResponseDTO;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PatchMapping("/me")
	public ResponseEntity<UpdateUserResponseDTO> updateUser(@AuthenticationPrincipal User user,
			@RequestBody @Valid UpdateUserRequestDTO data) {
		UpdateUserResponseDTO updateUser = userService.updateUser(user.getIdUsers(), data);

		return ResponseEntity.ok(updateUser);

	}

	@PatchMapping("/me/password")
	public ResponseEntity<Void> updatePassword(@AuthenticationPrincipal User user,
			@RequestBody @Valid UpdatePasswordRequestDTO data) {
		
		userService.updatePassword(user.getIdUsers(), data);
		
		return ResponseEntity.noContent().build();
	}

}
