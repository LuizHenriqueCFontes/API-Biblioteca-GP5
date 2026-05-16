package com.biblioteca.gp5.book.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.book.dto.request.EditBookRequestDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.dto.response.EditBookResponseDTO;
import com.biblioteca.gp5.book.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
	public ResponseEntity<Page<BookResponseDTO>> listBook(@RequestParam(required = false) String title, 
			@PageableDefault(page = 0, size = 20) Pageable pageable){
		
		Page<BookResponseDTO> response = bookService.listBook(title, pageable);
		
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{idBook}")
	public ResponseEntity<EditBookResponseDTO> editBook(@PathVariable UUID idBook, 
			@RequestBody @Valid EditBookRequestDTO request){
		
		EditBookResponseDTO response = bookService.editBook(idBook, request);
		
		return ResponseEntity.ok(response);
	}

}
