package com.biblioteca.gp5.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.user.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
	Optional<Users> findByEmail(String email); //Esse metodo sera uma query para buscar o usuario pelo email
	
	boolean existsByEmail(String email); //Esse metodo sera uma query para verificar se tem um email cadastrado no sistema
	
	/* 
	 * Retorna todos os usuários de forma paginada.
	 * Recebe um Pageable para definir:
	 * - qual página buscar (page)
	 * - quantos itens por página (size)
	 * 
	 * Usamos Page<Users> para evitar carregar todos os registros de uma vez,
	 * melhorando desempenho e facilitando paginação no front-end.
	 */
	Page<Users> findAll(Pageable pageable);
	
	//Nessa query eu passo um String username, para ter a possibilidade de buscar por nomes
	Page<Users> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
}
