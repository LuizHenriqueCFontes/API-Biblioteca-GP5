package com.biblioteca.gp5.book.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.book.dto.request.BookFilterRequestDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
	public ResponseEntity<Page<BookResponseDTO>> listBook(@RequestParam(required = false) BookFilterRequestDTO filter, 
			@PageableDefault(page = 0, size = 20) Pageable pageable){
		
		Page<BookResponseDTO> response = bookService.listBook(filter, pageable);
		
		return ResponseEntity.ok(response);
	}
	

}
