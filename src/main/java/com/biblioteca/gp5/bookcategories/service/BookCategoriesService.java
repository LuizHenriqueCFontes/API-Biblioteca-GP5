package com.biblioteca.gp5.bookcategories.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.bookcategories.dto.request.CreateBookCategoriesRequestDTO;
import com.biblioteca.gp5.bookcategories.dto.request.EditBookCategoriesRequestDTO;
import com.biblioteca.gp5.bookcategories.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.bookcategories.dto.response.CreateBookCategoriesResponseDTO;
import com.biblioteca.gp5.bookcategories.dto.response.EditBookCategoriesResponseDTO;
import com.biblioteca.gp5.bookcategories.mapper.BookCategoriesMapper;
import com.biblioteca.gp5.bookcategories.model.BookCategories;
import com.biblioteca.gp5.bookcategories.repository.BookCategoriesRepository;
import com.biblioteca.gp5.category.model.Category;
import com.biblioteca.gp5.category.repository.CategoryRepository;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.exception.category.CategoryNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class BookCategoriesService {
	
	private final BookCategoriesRepository bookCategoriesRepository;
	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;
	private final BookCategoriesMapper bookCategoriesMapper;
	
	public BookCategoriesService(BookCategoriesRepository bookCategoriesRepository, 
			BookRepository bookRepository, CategoryRepository categoryRepository, BookCategoriesMapper bookCategoryMapper) {
		
		this.bookCategoriesRepository = bookCategoriesRepository;
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository; 
		this.bookCategoriesMapper = bookCategoryMapper;
	}
	
	@Transactional
	public CreateBookCategoriesResponseDTO addCategories(UUID idBook, CreateBookCategoriesRequestDTO request) {
		Book book = bookRepository.findById(idBook)
									.orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));
		
		Set<UUID> categoriesNormalized = new LinkedHashSet<>(request.idCategory());
		
		List<UUID> categories = new ArrayList<>(categoriesNormalized);
		
		List<Category> listCategories = categoryRepository.findAllById(categories);
		
		if(listCategories.size() != categories.size()) {
			throw new CategoryNotFoundException("Categoria não encontrada");
		}
		
		List<BookCategories> bookCategories = listCategories.stream()
															.map(category -> new BookCategories(book, category))
															.toList();
		
		bookCategoriesRepository.saveAll(bookCategories);
		
		List<CategoryResponseDTO> categoriesResponse = bookCategoriesMapper.toListCategory(listCategories);
		
		CreateBookCategoriesResponseDTO response = new CreateBookCategoriesResponseDTO(book.getIdBook(), book.getTitle(), 
				categoriesResponse);
		
		return response;
	}
	
	@Transactional
	public EditBookCategoriesResponseDTO editBookCategories(UUID idBook, EditBookCategoriesRequestDTO request) {
		Book book = bookRepository.findById(idBook)
									.orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));
		
		Set<UUID> categoriesNormalized = new LinkedHashSet<>(request.idCategory());
		
		List<UUID> categories = new ArrayList<>(categoriesNormalized);
		
		List<Category> listCategories = categoryRepository.findAllById(categories);
		
		if(listCategories.size() != categories.size()) {
			throw new CategoryNotFoundException("Categoria não encontrado");
		}
		
		bookCategoriesRepository.deleteAllByIdBook(idBook);
		
		List<BookCategories> newBookCategories = listCategories.stream()
																.map(category -> new BookCategories(book, category))
																.toList();
		
		bookCategoriesRepository.saveAll(newBookCategories);
		
		List<CategoryResponseDTO> categoriesResponse = bookCategoriesMapper.toListCategory(listCategories);
		
		EditBookCategoriesResponseDTO response = new EditBookCategoriesResponseDTO(book.getIdBook(), book.getTitle(), 
				categoriesResponse);
		
		return response;
	}

}
