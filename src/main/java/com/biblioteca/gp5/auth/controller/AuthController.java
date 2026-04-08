package com.biblioteca.gp5.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.auth.dto.request.LoginDTO;
import com.biblioteca.gp5.auth.dto.request.RegisterDTO;
import com.biblioteca.gp5.auth.dto.response.AuthResponseDTO;
import com.biblioteca.gp5.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController //Defino um restController, quem ira receber a requisição do usuario
@RequestMapping("/auth") // Defino o endpoint "/auth"
public class AuthController {
	
	//Crio o service, a logica do endpoint auth
	private final AuthService authService;
	
	//Crio o construtor para inicia-lo
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	/*Faço o primeiro endpoint de auth, que sera o login, que sera post,
	Ele tera um retorno de de um response deicado, ira receber o dto que tera um valid, que foi definido as condições no dto do Login
	e ira transformar o objeto que veio no login, em objeto Java
	*/	
	@PostMapping("/login") 									
	public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDTO data){
		
		//Ira receber o valor do Service
		AuthResponseDTO response = authService.login(data);
		
		//Se tudo der certo, ira retornar o status ok
		return ResponseEntity.ok(response);	
	}
	
	/*Faço o segundo endpoint de auth, que sera o register, que sera post,
	Ele tera um retorno de de um response deicado, ira receber o dto que tera um valid, que foi definido as condições no dto do register
	e ira transformar o objeto que veio no login, em objeto Java
	*/						
	@PostMapping("/register") 				
	public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterDTO data){
		
		//Ira receber o valor do Service
		AuthResponseDTO response = authService.register(data);
		
		//Se tudo der certo, ira retornar o status created
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
