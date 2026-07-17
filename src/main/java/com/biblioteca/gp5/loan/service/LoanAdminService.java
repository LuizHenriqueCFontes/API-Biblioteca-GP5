package com.biblioteca.gp5.loan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.loan.dto.response.DetailsLoanResponseDTO;
import com.biblioteca.gp5.loan.mapper.LoanMapper;
import com.biblioteca.gp5.loan.model.Loan;
import com.biblioteca.gp5.loan.repository.LoanRepository;

@Service
public class LoanAdminService {
	
	private final LoanRepository loanRepository;
	private final LoanMapper loanMapper;
	
	public LoanAdminService(LoanRepository loanRepository, LoanMapper loanMapper) {
		this.loanRepository = loanRepository;
		this.loanMapper = loanMapper;
	}
	
	public List<DetailsLoanResponseDTO> listDetailsLoan() {
		List<Loan> loans = loanRepository.findTop5ByOrderByLoanDateDesc();
		
		List<DetailsLoanResponseDTO> response = loanMapper.toDetailsLoanResponseDTO(loans);
		
		return response;
	}

}
