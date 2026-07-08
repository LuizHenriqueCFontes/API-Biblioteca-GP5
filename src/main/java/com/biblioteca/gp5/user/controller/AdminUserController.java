package com.biblioteca.gp5.user.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.user.dto.request.UpdateRoleRequestDTO;
import com.biblioteca.gp5.user.dto.response.UserListResponseDTO;
import com.biblioteca.gp5.user.service.AdminUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/admin/users")
public class AdminUserController {
	
	private final AdminUserService adminUserService;
	
	public AdminUserController(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	@GetMapping
	public ResponseEntity<Page<UserListResponseDTO>> listUsers(@RequestParam(required = false) String username,
			@PageableDefault(page = 0, size = 20) Pageable pageable) {
		Page<UserListResponseDTO> users = adminUserService.listUsers(username, pageable);

		return ResponseEntity.ok(users);

	}

	@PatchMapping("/{id}/role")
	public ResponseEntity<Void> updateRole(@PathVariable UUID id, @RequestBody @Valid UpdateRoleRequestDTO data) {
		adminUserService.updateRole(id, data);

		return ResponseEntity.noContent().build();

	}
}
