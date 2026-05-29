package com.biblioteca.gp5.bookcategory.dto.response;

import java.util.UUID;

public record CreateBookCategoriesResponseDTO(UUID idBookCategory, UUID book, UUID category) {

}
