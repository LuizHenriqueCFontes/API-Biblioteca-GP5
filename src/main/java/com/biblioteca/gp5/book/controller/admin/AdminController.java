package com.biblioteca.gp5.book.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.service.BookManagementService;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;

@RestController
@RequestMapping("/api/admin/books")
public class AdminController {
	
	private final BookManagementService bookManagementService;
	
	public AdminController(BookManagementService bookManagementService) {
		this.bookManagementService = bookManagementService;
	}
	
	@GetMapping
	public ResponseEntity<GutendexSearchResponseDTO> searchBooksGutendex(@RequestParam(required = false) String title){
		
		GutendexSearchResponseDTO response = bookManagementService.gutendexSearchBooks(title);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<BookResponseDTO> saveBook(@PathVariable Integer id){
		BookResponseDTO response = bookManagementService.saveBook(id);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
