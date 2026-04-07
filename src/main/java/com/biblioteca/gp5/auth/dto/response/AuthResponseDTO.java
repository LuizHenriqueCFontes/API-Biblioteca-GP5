package com.biblioteca.gp5.auth.dto.response;

public record AuthResponseDTO(String type, String token, String username) {
	
	public AuthResponseDTO(String token, String username) {
		this("Bearer", token, username);
	}

}
