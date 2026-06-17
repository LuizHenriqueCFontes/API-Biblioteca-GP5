package com.biblioteca.gp5.loan.dto.response;

public record LoanSummaryResponseDTO(Integer activeLoans, Integer totalLoans, long nextDueDateInDays) {

}
