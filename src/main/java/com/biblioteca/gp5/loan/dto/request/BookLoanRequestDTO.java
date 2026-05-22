package com.biblioteca.gp5.loan.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record BookLoanRequestDTO(@NotNull UUID  bookId) {

}
