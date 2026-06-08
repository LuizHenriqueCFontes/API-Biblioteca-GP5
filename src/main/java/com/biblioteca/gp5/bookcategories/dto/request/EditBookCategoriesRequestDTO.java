package com.biblioteca.gp5.bookcategories.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record EditBookCategoriesRequestDTO(@NotNull List<UUID> idCategory) {

}
