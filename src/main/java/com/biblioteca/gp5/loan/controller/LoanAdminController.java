package com.biblioteca.gp5.loan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.loan.dto.response.DetailsLoanResponseDTO;
import com.biblioteca.gp5.loan.service.LoanAdminService;

@RestController
@RequestMapping("/api/admin/loan")
public class LoanAdminController {
	
	private final LoanAdminService loanAdminService;
	
	public LoanAdminController(LoanAdminService loanAdminService) {
		this.loanAdminService = loanAdminService;
	}
	
	@GetMapping
	public ResponseEntity<List<DetailsLoanResponseDTO>> listDetailsLoan() {
		List<DetailsLoanResponseDTO> response = loanAdminService.listDetailsLoan();
		
		return ResponseEntity.ok(response);
	}

}
