package com.biblioteca.gp5.loan.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PatchMapping("{idLoan}")
	public ResponseEntity<Void> returnLoan(@AuthenticationPrincipal User user, @PathVariable UUID idLoan) {
		loanService.returnLoanByUser(idLoan, user.getIdUsers());
		
		return ResponseEntity.noContent().build();
	}

}
