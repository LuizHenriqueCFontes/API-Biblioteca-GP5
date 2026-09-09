package com.biblioteca.gp5.passwordresettoken.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.gp5.passwordresettoken.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {
	
	@Modifying
	@Query("""
			DELETE FROM PasswordResetToken p
			WHERE p.expiresAt < :now
	""")
	void deleteExpiredTokens(@Param("now") LocalDateTime now);
}
