package com.biblioteca.gp5.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.gp5.user.model.Users;

public interface UserRepository extends JpaRepository<Users, String> {
	Optional<Users> findByEmail(String email); //Esse metodo sera uma query para buscar o usuario pelo email
	
	boolean existsByEmail(String email); //Esse metodo sera uma query para verificar se tem um email cadastrado no sistema
}
