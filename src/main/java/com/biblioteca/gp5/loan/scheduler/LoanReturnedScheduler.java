package com.biblioteca.gp5.loan.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.biblioteca.gp5.loan.service.LoanService;


@Component
public class LoanReturnedScheduler {
	
	private final LoanService loanService;
	
	public LoanReturnedScheduler(LoanService loanService) {
		this.loanService = loanService;
	}
	
	
	@Scheduled(cron = "0 0 * * * *")
	public void expiresLoan() {
		loanService.returnLoanBySystem();
		
	}

}
