package com.biblioteca.gp5.loan.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.loan.model.Loan;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.loan.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
public class LoanReturnedService {
	
	private final LoanRepository loanRepository;
	
	public LoanReturnedService(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}
	
	@Transactional
	public void returnedLoans() {
		
		List<Loan> expiredLoans = loanRepository.findByStatusAndExpectedReturnDateBefore(Status.ACTIVE, 
				LocalDateTime.now());
		
		expiredLoans.forEach(Loan::expiresLoan);
	}
	
}
