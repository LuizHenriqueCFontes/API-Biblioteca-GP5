package com.biblioteca.gp5.bookcategories.dto.response;

import java.util.List;
import java.util.UUID;

public record EditBookCategoriesResponseDTO(UUID idBook, String title, List<CategoryResponseDTO> categories) {

}
