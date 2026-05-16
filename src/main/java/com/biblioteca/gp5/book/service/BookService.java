package com.biblioteca.gp5.book.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.dto.request.EditBookRequestDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.dto.response.EditBookResponseDTO;
import com.biblioteca.gp5.book.mapper.BookMapper;
import com.biblioteca.gp5.book.model.Books;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.exception.book.BookNotFoundException;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	private final BookMapper bookMapper;
	
	public BookService(BookRepository bookRepository, BookMapper bookMapper) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
	}
	
	public Page<BookResponseDTO> listBook(String title, Pageable pageable){
		
		Page<Books> books;
		
		if(title == null || title.isBlank()) {
			books = bookRepository.findAll(pageable);
			
		} else {
			books = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
		}
		
		Page<BookResponseDTO> booksResponse = books.map(bookMapper::toBookResponseDTO);
		
		return booksResponse;
	}
	
	public EditBookResponseDTO editBook(UUID idBook, EditBookRequestDTO request) {
		Books book = bookRepository.findById(idBook)
									.orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));
		
		if(request.title() != null && !request.title().isBlank()) {
			book.setTitle(request.title());
		}
		
		if(request.authors() != null && !request.authors().isEmpty()) {
			book.setAuthors(request.authors());
		}
		
		if(request.description() != null && !request.description().isEmpty()) {
			book.setDescription(request.description());
		}
		
		if(request.source() != null && !request.source().isBlank()) {
			book.setSource(request.source());
		}
		
		if(request.totalQuantity() != null && request.totalQuantity() >= 0) {
			book.setTotalQuantity(request.totalQuantity());
		}
		
		if(request.availableQuantity() != null && request.availableQuantity() >= 0) {
			book.setAvailableQuantity(request.availableQuantity());
		}
		
		if(request.active() != null) {
			book.setActive(request.active());
		}
		
		bookRepository.save(book);
		
		EditBookResponseDTO response = bookMapper.toEditBookResponseDTO(book);
		
		return response;
	}

}
