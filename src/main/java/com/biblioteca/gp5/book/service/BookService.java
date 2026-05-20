package com.biblioteca.gp5.book.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.dto.request.EditBookRequestDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.dto.response.EditBookResponseDTO;
import com.biblioteca.gp5.book.mapper.BookMapper;
import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.exception.book.BookNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	private final BookMapper bookMapper;
	
	public BookService(BookRepository bookRepository, BookMapper bookMapper) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
	}
	
	public Page<BookResponseDTO> listBook(String title, Pageable pageable){
		
		Page<Book> books;
		
		if(title == null || title.isBlank()) {
			books = bookRepository.findAll(pageable);
			
		} else {
			books = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
		}
		
		Page<BookResponseDTO> booksResponse = books.map(bookMapper::toBookResponseDTO);
		
		return booksResponse;
	}

}
