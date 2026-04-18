CREATE TABLE reading(
	id_reading VARCHAR(36) UNIQUE NOT NULL,
    book_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    current_page INT DEFAULT 0,
	percentage DECIMAL(5, 2) DEFAULT 0.00,
    last_reading TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY(id_reading),
    
    UNIQUE(book_id, user_id),
    
    CONSTRAINT fk_reading_book
		FOREIGN KEY (book_id) REFERENCES books(id_books),
        
	CONSTRAINT fk_reading_user
		FOREIGN KEY (user_id) REFERENCES users(id_users)
);