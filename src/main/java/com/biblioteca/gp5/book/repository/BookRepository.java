package com.biblioteca.gp5.book.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
	Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
	
	Page<Book> findAll(Pageable pageable);

}
