package com.biblioteca.gp5.category.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;

public record DeleteCategoriesRequestDTO(@NotEmpty List<UUID> idCategories) {

}
