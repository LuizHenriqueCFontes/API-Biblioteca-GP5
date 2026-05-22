package com.biblioteca.gp5.loan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.loan.dto.request.BookLoanRequestDTO;
import com.biblioteca.gp5.loan.dto.response.BookLoanResponseDTO;
import com.biblioteca.gp5.loan.service.LoanService;
import com.biblioteca.gp5.user.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
	
	private final LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}
	
	@PostMapping
	public ResponseEntity<BookLoanResponseDTO> bookLoan(@AuthenticationPrincipal User user, 
			@RequestBody @Valid BookLoanRequestDTO request){
		
		BookLoanResponseDTO response = loanService.bookLoan(user.getIdUsers(), request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}

}
