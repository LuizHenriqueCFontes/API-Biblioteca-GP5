package com.biblioteca.gp5.book.service;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.enums.BookFormat;
import com.biblioteca.gp5.book.enums.BookSources;
import com.biblioteca.gp5.book.enums.BookCover;
import com.biblioteca.gp5.book.mapper.BookMapper;
import com.biblioteca.gp5.book.model.Books;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.integration.gutendex.client.GutendexClient;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexBookResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;

@Service
public class BookManagementService {
	
	private final GutendexClient gutendexClient;
	private final BookMapper bookMapper;
	private final BookRepository bookRepository;
	
	public BookManagementService(GutendexClient gutendexClient, BookMapper bookMapper, BookRepository bookRepository) {
		this.gutendexClient = gutendexClient;
		this.bookMapper = bookMapper;
		this.bookRepository = bookRepository;
	}
	
	public GutendexSearchResponseDTO gutendexSearchBooks(String title) {
		return gutendexClient.searchBooks(title);
		
	}
	
	public BookResponseDTO saveBook(Integer id) {
		GutendexBookResponseDTO gutendexBook = gutendexClient.searchBookById(id);
		
		Books book = bookMapper.toEntity(gutendexBook);
		
		bookRepository.save(book);
		
		BookResponseDTO response = bookMapper.toBookResponseDTO(book);
		
		return response;
	}
	
}
