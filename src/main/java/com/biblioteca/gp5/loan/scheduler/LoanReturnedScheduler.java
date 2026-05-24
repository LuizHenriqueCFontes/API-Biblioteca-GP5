package com.biblioteca.gp5.loan.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.biblioteca.gp5.loan.service.LoanReturnedService;


@Component
public class LoanReturnedScheduler {
	
	private final LoanReturnedService loanReturnedService;
	
	public LoanReturnedScheduler(LoanReturnedService loanReturnedService) {
		this.loanReturnedService = loanReturnedService;
	}
	
	
	@Scheduled(cron = "0 0 * * * *")
	public void expiresLoan() {
		loanReturnedService.returnedLoans();
		
	}

}
