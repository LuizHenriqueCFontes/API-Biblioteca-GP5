package com.biblioteca.gp5.book.dto.response;

import java.util.List;
import java.util.UUID;

public record EditBookResponseDTO(UUID idBook, String title, List<String> authors, List<String> description, String source, 
		Integer totalQuantity, Integer availableQuantity, Boolean active) {

}
