CREATE TABLE loans(
	id_loans VARCHAR(36) UNIQUE NOT NULL,
    book_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    type ENUM('ONLINE', 'PHYSICAL') NOT NULL,
    status ENUM('ACTIVE', 'RETURNED', 'OVERDUE') NOT NULL,
    loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expected_return_date TIMESTAMP NOT NULL,
    actual_return_date TIMESTAMP,
    
    PRIMARY KEY(id_loans),
    
    CONSTRAINT fk_loans_book
		FOREIGN KEY (book_id) REFERENCES books(id_books),
        
	CONSTRAINT fk_loans_user
		FOREIGN KEY (user_id) REFERENCES users(id_users)
);