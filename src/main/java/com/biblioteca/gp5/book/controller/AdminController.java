package com.biblioteca.gp5.book.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.book.dto.request.EditBookRequestDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.dto.response.EditBookResponseDTO;
import com.biblioteca.gp5.book.dto.response.ImportSearchResponseDTO;
import com.biblioteca.gp5.book.service.BookManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/books")
public class AdminController {
	
	private final BookManagementService bookManagementService;
	
	public AdminController(BookManagementService bookManagementService) {
		this.bookManagementService = bookManagementService;
	}
	
	@GetMapping
	public ResponseEntity<ImportSearchResponseDTO> searchBooksGutendex(@RequestParam(required = false) String title){
		
		ImportSearchResponseDTO response = bookManagementService.gutendexSearchBooks(title);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<BookResponseDTO> saveBook(@PathVariable Integer id){
		BookResponseDTO response = bookManagementService.saveBook(id);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PatchMapping("/{idBook}")
	public ResponseEntity<EditBookResponseDTO> editBook(@PathVariable UUID idBook, 
			@RequestBody @Valid EditBookRequestDTO request){
		
		EditBookResponseDTO response = bookManagementService.editBook(idBook, request);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{idBook}")
	public ResponseEntity<Void> deleteBook(@PathVariable UUID idBook){
		bookManagementService.deleteBook(idBook);
		
		return ResponseEntity.noContent().build();
	}
}
