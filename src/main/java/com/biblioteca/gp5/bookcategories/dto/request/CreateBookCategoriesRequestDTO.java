package com.biblioteca.gp5.bookcategories.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateBookCategoriesRequestDTO(@NotNull List<UUID> idCategory) {

}
