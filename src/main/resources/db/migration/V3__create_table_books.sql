CREATE TABLE books(
	id_books VARCHAR(36) UNIQUE NOT NULL,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    description TEXT,
    cover_url VARCHAR(255),
    gutenberg_id VARCHAR(20) UNIQUE,
    file_url VARCHAR(500),
    file_type VARCHAR(20),
    source VARCHAR(30) NOT NULL,
    pages INT,
    total_quantity INT DEFAULT 0,
    available_quantity INT DEFAULT 0,
    active BOOLEAN DEFAULT TRUE,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
    PRIMARY KEY(id_books)
);