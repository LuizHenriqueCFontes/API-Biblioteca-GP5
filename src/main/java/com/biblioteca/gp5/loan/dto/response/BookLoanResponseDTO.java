package com.biblioteca.gp5.loan.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.biblioteca.gp5.loan.model.enums.Status;

public record BookLoanResponseDTO(UUID idLoan, String title, UUID bookId, Status status, LocalDateTime loanDate, 
		LocalDateTime expectedReturnDate) {

}
