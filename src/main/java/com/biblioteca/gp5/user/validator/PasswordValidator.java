package com.biblioteca.gp5.user.validator;

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
		
		if(data.newPassword().length() < 8) {
			throw new InvalidPasswordException("A senha deve ter no minímo 8 caracteres");
		}
		
		if(!data.newPassword().equals(data.confirmNewPassword())) {
			throw new InvalidPasswordException("As senhas informadas não são iguais");
		}
	}
}
