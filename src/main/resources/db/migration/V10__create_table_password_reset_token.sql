CREATE TABLE password_reset_tokens(
	id BINARY(16) UNIQUE NOT NULL,
	token_hash VARCHAR (255) NOT NULL,
	user_id BINARY(16) UNIQUE NOT NULL,
	expires_at DATETIME NOT NULL,
	
	PRIMARY KEY (id),
	
	CONSTRAINT fk_password_reset_token_user
		FOREIGN KEY (user_id) REFERENCES users (id_users)
);