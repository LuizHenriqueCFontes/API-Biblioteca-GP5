package com.biblioteca.gp5.bookcategories.dto.response;

import java.util.List;
import java.util.UUID;

import com.biblioteca.gp5.category.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.model.Category;

public record CreateBookCategoriesResponseDTO(UUID idBook, String title, List<CategoryResponseDTO> categories) {

}
