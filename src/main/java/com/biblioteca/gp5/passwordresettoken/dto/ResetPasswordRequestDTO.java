package com.biblioteca.gp5.passwordresettoken.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDTO(
		
		@NotBlank
		String token,
		
		@NotBlank
		String password,
		
		@NotBlank
		String confirmPassword
		
		) {}
