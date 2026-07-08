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
import com.biblioteca.gp5.book.enums.BookCover;
import com.biblioteca.gp5.book.enums.BookFormat;
import com.biblioteca.gp5.book.mapper.BookMapper;
import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.exception.book.BookAlreadyRegisteredException;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.integration.gutendex.client.GutendexClient;
import com.biblioteca.gp5.integration.gutendex.download.FileDownloadService;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexBookResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;
import com.biblioteca.gp5.integration.gutendex.mapper.GutendexMapper;
import com.biblioteca.gp5.storage.StorageService;

import jakarta.transaction.Transactional;

@Service
public class BookManagementService {
	
	private final GutendexClient gutendexClient;
	private final BookMapper bookMapper;
	private final BookRepository bookRepository;
	private final GutendexMapper gutendexMapper;
	private final FileDownloadService fileDownload;
	private final StorageService storageService;
	
	public BookManagementService(GutendexClient gutendexClient, BookMapper bookMapper, BookRepository bookRepository, 
			GutendexMapper gutendexMapper, FileDownloadService fileDownload, StorageService storageService) {
		this.gutendexClient = gutendexClient;
		this.bookMapper = bookMapper;
		this.bookRepository = bookRepository;
		this.gutendexMapper = gutendexMapper;
		this.fileDownload = fileDownload;
		this.storageService = storageService;
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
		
		if(bookRepository.existsByGutenbergId(id)) {
			throw new BookAlreadyRegisteredException("Livro ja está cadastrado");
		}
		
		GutendexBookResponseDTO gutendexBook = gutendexClient.searchBookById(id);
		
		String coverUrl = gutendexBook.formats().get(BookCover.IMAGE.getValue());
		String epubUrl = gutendexBook.formats().get(BookFormat.EPUB.getValue());
		
		byte[] coverBytes = fileDownload.download(coverUrl);
		byte[] epubBytes = fileDownload.download(epubUrl);
		
		String coverDirectory = storageService.saveCover(coverBytes);
		String epubDirectory = storageService.saveEpub(epubBytes);
				
		Book book = bookMapper.toEntity(gutendexBook);
		
		book.setCoverUrl(coverDirectory);
		book.setFileUrl(epubDirectory);
		
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
