package com.biblioteca.gp5.bookcategory.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateBookCategoriesRequestDTO(@NotNull UUID idCategory) {

}
