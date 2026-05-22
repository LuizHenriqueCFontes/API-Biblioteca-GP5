package com.biblioteca.gp5.loan.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.loan.model.Loan;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.loan.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
public class LoanExpirationService {
	
	private final LoanRepository loanRepository;
	
	public LoanExpirationService(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}
	
	@Transactional
	public void expireLoans() {
		
		List<Loan> expiredLoans = loanRepository.findByStatusAndExpirationDateBefore(Status.ACTIVE, LocalDateTime.now());
		
		expiredLoans.forEach(Loan::expiresLoan);
	}
	
}
