package com.biblioteca.gp5.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.book.service.BookManagementService;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookManagementService bookManagementService;
	
	public BookController(BookManagementService bookManagementService) {
		this.bookManagementService = bookManagementService;
	}
	
	public ResponseEntity<GutendexSearchResponseDTO> searchBooksGutendex(@RequestParam(required = false) String title){
		GutendexSearchResponseDTO response = bookManagementService.gutendexSearchBooks(title);
		
		return ResponseEntity.ok(response);
	}

}
