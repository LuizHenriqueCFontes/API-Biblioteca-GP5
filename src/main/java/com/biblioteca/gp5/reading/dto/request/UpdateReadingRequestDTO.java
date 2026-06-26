package com.biblioteca.gp5.reading.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record UpdateReadingRequestDTO(@NotNull String epubCfi, BigDecimal percentage) {

}
