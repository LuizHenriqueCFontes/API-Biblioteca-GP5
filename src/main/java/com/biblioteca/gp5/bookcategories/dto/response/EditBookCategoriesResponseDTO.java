package com.biblioteca.gp5.bookcategories.dto.response;

import java.util.List;
import java.util.UUID;

import com.biblioteca.gp5.category.dto.response.ListCategoryResponseDTO;

public record EditBookCategoriesResponseDTO(UUID idBook, String title, List<ListCategoryResponseDTO> categories) {

}
