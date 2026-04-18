CREATE TABLE books(
	id_books VARCHAR(36) UNIQUE NOT NULL,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    description TEXT,
    cover_url VARCHAR(255),
    total_quantity INT DEFAULT 0,
    available_quantity INT DEFAULT 0,
    active BOOLEAN DEFAULT TRUE,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
    PRIMARY KEY(id_books)
);