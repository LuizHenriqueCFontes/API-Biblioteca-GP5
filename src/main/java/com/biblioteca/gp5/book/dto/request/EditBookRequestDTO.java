package com.biblioteca.gp5.book.dto.request;

import java.util.List;


public record EditBookRequestDTO(
		
		String title, 
		
		List<String> authors, 
		
		List<String> description
		) {

}
