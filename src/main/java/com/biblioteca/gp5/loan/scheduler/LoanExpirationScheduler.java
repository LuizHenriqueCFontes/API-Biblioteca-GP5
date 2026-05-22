package com.biblioteca.gp5.loan.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.biblioteca.gp5.loan.service.LoanExpirationService;

@Component
public class LoanExpirationScheduler {
	
	private final LoanExpirationService loanExpirationService;
	
	public LoanExpirationScheduler(LoanExpirationService loanExpirationService) {
		this.loanExpirationService = loanExpirationService;
	}
	
	@Scheduled(cron = "0 0 * * * *")
	public void expiresLoan() {
		
		loanExpirationService.expireLoans();
	}

}
