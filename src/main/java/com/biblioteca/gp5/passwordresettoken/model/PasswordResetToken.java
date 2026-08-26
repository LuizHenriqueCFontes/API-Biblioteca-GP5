package com.biblioteca.gp5.passwordresettoken.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.biblioteca.gp5.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "password_reset_tokens")
@Data
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "token_hash")
	private String tokenHash;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	private LocalDateTime expiresAt;

}
