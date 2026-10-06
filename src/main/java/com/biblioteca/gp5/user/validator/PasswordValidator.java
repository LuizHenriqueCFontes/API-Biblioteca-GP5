package com.biblioteca.gp5.user.validator;

import org.springframework.stereotype.Component;

import com.biblioteca.gp5.exception.user.InvalidPasswordException;
import com.biblioteca.gp5.user.dto.request.UpdatePasswordRequestDTO;

@Component
public class PasswordValidator {
	
	//Metodo para verificacao de senha
	public void validate(String newPassword, String confirmNewPassword) {
		
		//Irei verificar se a senha veio vazia
		if(confirmNewPassword == null || confirmNewPassword.isBlank()) {
			throw new InvalidPasswordException("Senha inválida");
		}
		
		if(confirmNewPassword.length() < 8) {
			throw new InvalidPasswordException("A senha deve ter no minímo 8 caracteres");
		}
		
		if(!newPassword.equals(confirmNewPassword)) {
			throw new InvalidPasswordException("As senhas informadas não são iguais");
		}
	}
}
