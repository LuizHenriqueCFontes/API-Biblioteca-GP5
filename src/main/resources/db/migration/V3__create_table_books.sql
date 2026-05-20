CREATE TABLE books(
	id_books BINARY(16) UNIQUE NOT NULL,
    title VARCHAR(200) NOT NULL,
    authors VARCHAR(150) NOT NULL,
    description TEXT,
    cover_url VARCHAR(255),
    gutenberg_id INT UNIQUE,
    file_url VARCHAR(500),
    source VARCHAR(30) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
    PRIMARY KEY(id_books)
);