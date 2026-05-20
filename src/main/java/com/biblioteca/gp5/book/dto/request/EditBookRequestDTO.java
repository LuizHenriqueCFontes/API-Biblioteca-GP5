package com.biblioteca.gp5.book.dto.request;

import java.util.List;

import jakarta.validation.constraints.PositiveOrZero;

public record EditBookRequestDTO(
		
		String title, 
		
		List<String> authors, 
		
		List<String> description, 
		
		String source,
		
		Boolean active) {

}
