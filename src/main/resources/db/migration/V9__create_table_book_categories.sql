CREATE TABLE book_categories(
	id_books BINARY(16) NOT NULL,
    id_categories BINARY(16) NOT NULL,
    
    PRIMARY KEY(id_books, id_categories),
    
    CONSTRAINT fK_book_categories_books
    FOREIGN KEY (id_books) REFERENCES books(id_books),
    
    CONSTRAINT fk_book_categories_categories
    FOREIGN KEY (id_categories) REFERENCES categories(id_categories)
);