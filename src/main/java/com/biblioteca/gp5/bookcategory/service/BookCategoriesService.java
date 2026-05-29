package com.biblioteca.gp5.bookcategory.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.bookcategory.dto.request.CreateBookCategoriesRequestDTO;
import com.biblioteca.gp5.bookcategory.dto.response.CreateBookCategoriesResponseDTO;
import com.biblioteca.gp5.bookcategory.repository.BookCategoriesRepository;

import jakarta.transaction.Transactional;

@Service
public class BookCategoriesService {
	
	private final BookCategoriesRepository bookCategoryRepository;
	
	public BookCategoriesService(BookCategoriesRepository bookCategoryRepository) {
		this.bookCategoryRepository = bookCategoryRepository;
	}
	
	@Transactional
	public CreateBookCategoriesResponseDTO addCategories(UUID idBook, List<CreateBookCategoriesRequestDTO> request) {
		
		
		
	}

}
