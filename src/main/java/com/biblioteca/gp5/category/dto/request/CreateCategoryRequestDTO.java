package com.biblioteca.gp5.category.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequestDTO(@NotBlank String name) {

}
