package com.biblioteca.gp5.user.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.biblioteca.gp5.exception.user.InvalidPasswordException;
import com.biblioteca.gp5.user.dto.request.UpdatePasswordDTO;

@Component
public class PasswordValidator {
	
	//Metodo para verificacao de senha
	public void validate(UpdatePasswordDTO data) {
		
		//Irei verificar se a senha veio vazia
		if(data.newPassword() == null || data.newPassword().isBlank()) {
			throw new InvalidPasswordException("Senha inválida");
		}
	}
}
