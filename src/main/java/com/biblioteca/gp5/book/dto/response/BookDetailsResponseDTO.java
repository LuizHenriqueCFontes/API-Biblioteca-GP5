package com.biblioteca.gp5.book.dto.response;

import java.util.List;
import java.util.UUID;

import com.biblioteca.gp5.category.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.ListCategoryResponseDTO;

public record BookDetailsResponseDTO(UUID id, String title, List<String> authors, List<String> description, List<ListCategoryResponseDTO> categories, 
		String coverUrl, String fileUrl) {

}
