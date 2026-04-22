package com.biblioteca.gp5.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordDTO(
		
		@NotBlank
		String oldPassword, 
		
		@NotBlank
		@Size(min = 8)
		String newPassword,
		
		@NotBlank
		@Size(min = 8)
		String confirmNewPassword) {

}
