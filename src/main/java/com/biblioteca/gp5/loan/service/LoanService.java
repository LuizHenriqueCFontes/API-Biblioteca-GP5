package com.biblioteca.gp5.loan.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.exception.loan.UserHasLoanException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.loan.dto.request.BookLoanRequestDTO;
import com.biblioteca.gp5.loan.dto.response.BookLoanResponseDTO;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.loan.repository.LoanRepository;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;

@Service
public class LoanService {
	
	private final LoanRepository loanRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	
	public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
		this.loanRepository = loanRepository;
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}
	
	public BookLoanResponseDTO bookLoan(UUID userId, BookLoanRequestDTO request) {
		User user = userRepository.findById(userId)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		Book book = bookRepository.findById(request.bookId())
									.orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));
		
		if(loanRepository.existsByUserAndBookAndStatus(userId, book.getIdBook(), Status.ACTIVE)) {
			throw new UserHasLoanException("Usuário já possui o livro emprestado");
		}
		
		
		
		
		
	}
}
