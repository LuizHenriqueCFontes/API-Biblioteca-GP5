package com.biblioteca.gp5.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.user.repository.UserRepository;

/*Criei uma classe de UserDetailsService, ela sera responsavel por buscar o usuario no db*/
@Service
public class AuthorizationService implements UserDetailsService {
	
	//Crio uma dependencia com o userRepository, para realmente ir buscar os dados no db
	private final UserRepository userRepository;
	
	//Construtor para inserir o UserRepository
	public AuthorizationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*Aqui eu tenho um metodo que tem o retorno de UserDetails, que o meu model de Users implementa essa classe,
	 como o userDetailsService em si nao busca o usuario, dentro do metodo dele eu tenho o userRepository buscando o usuario, pelo metodo dele*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findById(username)
							.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
							
							
		
	}

}