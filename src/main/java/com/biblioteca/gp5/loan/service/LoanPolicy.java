package com.biblioteca.gp5.loan.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LoanPolicy {
	
	private static final int DEFAULT_LOAN_DAYS = 7;
	
	public LocalDateTime calculateExpirationDate(LocalDateTime loanDate) {
		
		return loanDate.plusDays(DEFAULT_LOAN_DAYS);
	}

}
