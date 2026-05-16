package com.biblioteca.gp5.user.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.user.dto.request.UpdatePasswordRequestDTO;
import com.biblioteca.gp5.user.dto.request.UpdateRoleRequestDTO;
import com.biblioteca.gp5.user.dto.request.UpdateUserRequestDTO;
import com.biblioteca.gp5.user.dto.response.UserListResponseDTO;
import com.biblioteca.gp5.user.dto.response.UpdateUserResponseDTO;
import com.biblioteca.gp5.user.model.Users;
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
	public ResponseEntity<UpdateUserResponseDTO> updateUser(@AuthenticationPrincipal Users user,
			@RequestBody @Valid UpdateUserRequestDTO data) {
		UpdateUserResponseDTO updateUser = userService.updateUser(user.getIdUsers(), data);

		return ResponseEntity.ok(updateUser);

	}

	@GetMapping
	public ResponseEntity<Page<UserListResponseDTO>> listUsers(@RequestParam(required = false) String username,
			@PageableDefault(page = 0, size = 20) Pageable pageable) {
		Page<UserListResponseDTO> users = userService.listUsers(username, pageable);

		return ResponseEntity.ok(users);

	}

	@PatchMapping("/{id}/role")
	public ResponseEntity<Void> updateRole(@PathVariable UUID id, @RequestBody @Valid UpdateRoleRequestDTO data) {
		userService.updateRole(id, data);

		return ResponseEntity.noContent().build();

	}

	@PatchMapping("/me/password")
	public ResponseEntity<Void> updatePassword(@AuthenticationPrincipal Users user,
			@RequestBody @Valid UpdatePasswordRequestDTO data) {
		
		userService.updatePassword(user.getIdUsers(), data);
		
		return ResponseEntity.noContent().build();
	}

}
