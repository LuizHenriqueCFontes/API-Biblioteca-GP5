package com.biblioteca.gp5.user.dto.request;

import jakarta.validation.constraints.Email;

public record UpdateUserDTO(
		
		String username, 
		
		@Email
		String email, 
		
		String phone) {

}
