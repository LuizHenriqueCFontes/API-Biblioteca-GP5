package com.biblioteca.gp5.loan.dto.response;

import java.time.LocalDateTime;

public record DetailsLoanResponseDTO(String username, String title, LocalDateTime loanDate, LocalDateTime expectedReturnDate) {

}
