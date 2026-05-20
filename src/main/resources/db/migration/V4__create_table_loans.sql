CREATE TABLE loans(
	id_loans BINARY(16) UNIQUE NOT NULL,
    book_id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    status ENUM('ACTIVE', 'EXPIRED') NOT NULL,
    loan_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    expiration_date DATETIME NOT NULL,
    
    PRIMARY KEY(id_loans),
    
    CONSTRAINT fk_loans_book
		FOREIGN KEY (book_id) REFERENCES books(id_books),
        
	CONSTRAINT fk_loans_user
		FOREIGN KEY (user_id) REFERENCES users(id_users)
);