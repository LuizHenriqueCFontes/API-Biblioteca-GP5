package com.biblioteca.gp5.passwordresettoken.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequestDTO(
		
		@NotBlank
		@Email
		String email
		
		
		) {}
