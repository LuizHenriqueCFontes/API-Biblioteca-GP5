package com.biblioteca.gp5.book.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.dto.request.EditBookRequestDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.dto.response.EditBookResponseDTO;
import com.biblioteca.gp5.book.dto.response.ImportBookDetailsResponseDTO;
import com.biblioteca.gp5.book.dto.response.ImportBookResponseDTO;
import com.biblioteca.gp5.book.dto.response.ImportSearchResponseDTO;
import com.biblioteca.gp5.book.mapper.BookMapper;
import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.integration.gutendex.client.GutendexClient;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexBookResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;
import com.biblioteca.gp5.integration.gutendex.mapper.GutendexMapper;

import jakarta.transaction.Transactional;

@Service
public class BookManagementService {
	
	private final GutendexClient gutendexClient;
	private final BookMapper bookMapper;
	private final BookRepository bookRepository;
	private final GutendexMapper gutendexMapper;
	
	public BookManagementService(GutendexClient gutendexClient, BookMapper bookMapper, BookRepository bookRepository, GutendexMapper gutendexMapper) {
		this.gutendexClient = gutendexClient;
		this.bookMapper = bookMapper;
		this.bookRepository = bookRepository;
		this.gutendexMapper = gutendexMapper;
	}
	
	public ImportSearchResponseDTO gutendexSearchBooks(String title, Integer page) {
		 GutendexSearchResponseDTO gutendexSearch = gutendexClient.searchBooks(title, page);
		 
		 List<ImportBookResponseDTO> results = gutendexSearch.results()
				 											.stream()
				 											.limit(20)
				 											.map(gutendexMapper::toImportBookResponseDTO)
				 											.toList();
		 
		ImportSearchResponseDTO response = new ImportSearchResponseDTO(gutendexSearch.count(), gutendexSearch.next(), 
				gutendexSearch.previous(), results);
		
		return response;
	}
	
	public ImportBookDetailsResponseDTO gutendexDetailsBooks(Integer id) {
		GutendexBookResponseDTO detailsBook = gutendexClient.searchBookById(id);
		
		ImportBookDetailsResponseDTO response = gutendexMapper.toImporBookDetailsResponseDTO(detailsBook);
		
		return response;
	}
	
	public BookResponseDTO saveBook(Integer id) {
		GutendexBookResponseDTO gutendexBook = gutendexClient.searchBookById(id);
				
		Book book = bookMapper.toEntity(gutendexBook);
		
		bookRepository.save(book);
		
		BookResponseDTO response = bookMapper.toBookResponseDTO(book);
		
		return response;
	}
	
	@Transactional
	public EditBookResponseDTO editBook(UUID idBook, EditBookRequestDTO request) {
		Book book = bookRepository.findById(idBook)
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
		
		
		bookRepository.save(book);
		
		EditBookResponseDTO response = bookMapper.toEditBookResponseDTO(book);
		
		return response;
	}
	
	@Transactional
	public void deleteBook(UUID idBook) {
		Book book = bookRepository.findById(idBook)
									.orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));
		
		book.setActive(false);
		
		bookRepository.save(book);
	}
	
}
