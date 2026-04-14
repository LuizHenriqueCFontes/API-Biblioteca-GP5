package com.biblioteca.gp5.user.controller;

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

import com.biblioteca.gp5.user.dto.request.UpdatePasswordDTO;
import com.biblioteca.gp5.user.dto.request.UpdateRoleDTO;
import com.biblioteca.gp5.user.dto.request.UpdateUserDTO;
import com.biblioteca.gp5.user.dto.response.ListResponseDTO;
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
			@RequestBody @Valid UpdateUserDTO data) {
		UpdateUserResponseDTO updateUser = userService.updateUser(user.getIdUsers(), data);

		return ResponseEntity.ok(updateUser);

	}

	@GetMapping
	public ResponseEntity<Page<ListResponseDTO>> listUsers(@RequestParam(required = false) String username,
			@PageableDefault(page = 0, size = 20) Pageable pageable) {
		Page<ListResponseDTO> users = userService.listUsers(username, pageable);

		return ResponseEntity.ok(users);

	}

	@PatchMapping("/{id}/role")
	public ResponseEntity<Void> updateRole(@PathVariable String id, @RequestBody @Valid UpdateRoleDTO data) {
		userService.updateRole(id, data);

		return ResponseEntity.noContent().build();

	}

	@PatchMapping("/me/password")
	public ResponseEntity<Void> updatePassword(@AuthenticationPrincipal Users user,
			@RequestBody @Valid UpdatePasswordDTO data) {
		
		userService.updatePassword(user.getIdUsers(), data);
		
		return ResponseEntity.noContent().build();
	}

}
