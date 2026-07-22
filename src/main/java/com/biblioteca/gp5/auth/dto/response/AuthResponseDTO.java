package com.biblioteca.gp5.auth.dto.response;

import com.biblioteca.gp5.user.model.UserRole;

public record AuthResponseDTO(String type, String token, String username, UserRole role) {
	
	public AuthResponseDTO(String token, String username, UserRole role) {
		this("Bearer", token, username, role);
	}

}
