package com.biblioteca.gp5.bookcategories.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.bookcategories.dto.request.EditBookCategoriesRequestDTO;
import com.biblioteca.gp5.bookcategories.dto.response.EditBookCategoriesResponseDTO;
import com.biblioteca.gp5.bookcategories.service.BookCategoriesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book-categories")
public class BookCategoriesController {
	
	private final BookCategoriesService bookCategoriesService;
	
	public BookCategoriesController(BookCategoriesService bookCategoriesService) {
		this.bookCategoriesService = bookCategoriesService;
	}
	
	@PutMapping("/{idBook}")
	public ResponseEntity<EditBookCategoriesResponseDTO> editBookCategories(@PathVariable UUID idBook, 
			@RequestBody @Valid EditBookCategoriesRequestDTO request) {
		
		EditBookCategoriesResponseDTO response = bookCategoriesService.editBookCategories(idBook, request);
		
		return ResponseEntity.ok(response);
		
	}
	
}
