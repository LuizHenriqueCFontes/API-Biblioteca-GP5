package com.biblioteca.gp5.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequestDTO(
		
		@NotBlank
		String oldPassword, 
		
		@NotBlank
		String newPassword,
		
		@NotBlank
		String confirmNewPassword) {

}
