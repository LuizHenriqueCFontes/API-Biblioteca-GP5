package com.biblioteca.gp5.passwordresettoken.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.gp5.passwordresettoken.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {

}
