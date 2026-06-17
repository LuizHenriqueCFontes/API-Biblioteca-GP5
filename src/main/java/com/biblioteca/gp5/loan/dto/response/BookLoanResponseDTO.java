package com.biblioteca.gp5.loan.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.biblioteca.gp5.loan.model.enums.Status;

public record BookLoanResponseDTO(UUID idLoan, String title, List<String> authors, UUID bookId, String coverUrl, String fileUrl, Status status, LocalDateTime loanDate, 
		LocalDateTime expectedReturnDate, LocalDateTime actualReturnDate) {

}
