package com.biblioteca.gp5.loan.dto.response;

import java.time.LocalDateTime;

import com.biblioteca.gp5.loan.model.enums.Status;

public record BookLoanResponseDTO(Status status, LocalDateTime loanDate, LocalDateTime expirationDate) {

}
