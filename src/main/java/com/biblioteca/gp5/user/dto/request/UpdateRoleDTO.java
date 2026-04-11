package com.biblioteca.gp5.user.dto.request;

import com.biblioteca.gp5.user.model.UserRole;

import jakarta.validation.constraints.NotNull;

public record UpdateRoleDTO(
		
		@NotNull
		UserRole role) {

}
