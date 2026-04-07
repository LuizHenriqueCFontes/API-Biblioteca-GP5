package com.biblioteca.gp5.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
		@NotBlank
		String username,
		
		@NotBlank
		@Email
		String email,
		
		@NotBlank
		String phone,
		
		@NotBlank
		String password) {

}
