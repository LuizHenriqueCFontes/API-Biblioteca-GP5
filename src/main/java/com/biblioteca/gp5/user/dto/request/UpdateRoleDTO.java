package com.biblioteca.gp5.user.dto.request;

import com.biblioteca.gp5.user.model.UserRole;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoleDTO(
		
		@NotBlank
		UserRole role) {

}
