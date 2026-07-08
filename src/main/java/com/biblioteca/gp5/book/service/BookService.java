package com.biblioteca.gp5.book.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.dto.request.BookFilterRequestDTO;
import com.biblioteca.gp5.book.dto.response.BookDetailsResponseDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.mapper.BookMapper;
import com.biblioteca.gp5.book.model.Book;
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
	
	public Page<BookResponseDTO> listBook(BookFilterRequestDTO filter, Pageable pageable){
		
		boolean hasTitle = filter.title() != null && !filter.title().isBlank();
		boolean hasCategory = filter.idsCategories() != null && !filter.idsCategories().isEmpty();
		
		Page<Book> books;
		
		if(!hasTitle && !hasCategory) {
			books = bookRepository.findByActiveTrue(pageable);
			
		} else if(hasTitle && hasCategory){
			books = bookRepository.findByTitleAndCategoriesAndActive(filter.title(), filter.idsCategories(), pageable);
			
			
		}else if(hasTitle && !hasCategory) {
			books = bookRepository.findByTitleContainingIgnoreCaseAndActiveTrue(filter.title(), pageable);
			
		}else {
			books = bookRepository.findByCategoriesAndActive(filter.idsCategories(), pageable);
		}
		
		Page<BookResponseDTO> booksResponse = books.map(bookMapper::toBookResponseDTO);
		
		return booksResponse;
	}
	
	public BookDetailsResponseDTO bookDetails(UUID idBook) {
		Book book = bookRepository.findById(idBook)
								.orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));
		
		BookDetailsResponseDTO response = bookMapper.toBookDetailsResponseDTO(book);
		
		return response;
	}

}
