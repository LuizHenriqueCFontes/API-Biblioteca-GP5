package com.biblioteca.gp5.loan.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.exception.book.BookNotAvailableException;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.exception.loan.LoanNotFoundException;
import com.biblioteca.gp5.exception.loan.UserHasLoanException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.loan.dto.request.BookLoanRequestDTO;
import com.biblioteca.gp5.loan.dto.response.BookLoanResponseDTO;
import com.biblioteca.gp5.loan.dto.response.LoanSummaryResponseDTO;
import com.biblioteca.gp5.loan.mapper.LoanMapper;
import com.biblioteca.gp5.loan.model.Loan;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.loan.repository.LoanRepository;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class LoanService {
	
	private final LoanRepository loanRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	private final LoanPolicy loanPolicy;
	private final LoanMapper loanMapper;
	
	public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, 
			LoanPolicy loanPolicy, LoanMapper loanMapper) {
		this.loanRepository = loanRepository;
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
		this.loanPolicy = loanPolicy;
		this.loanMapper = loanMapper;
	}
	
	@Transactional
	public BookLoanResponseDTO bookLoan(UUID userId, BookLoanRequestDTO request) {
		User user = userRepository.findById(userId)
									.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		Book book = bookRepository.findById(request.bookId())
									.orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));
		
		if(loanRepository.existsByUserAndBookAndStatus(user, book, Status.ACTIVE)) {
			throw new UserHasLoanException("Usuário já possui o livro emprestado");
		}
		
		if(!book.isActive()) {
			throw new BookNotAvailableException("Livro não disponível");
		}
		
		Loan loan = new Loan();
		
		LocalDateTime loanDate = LocalDateTime.now();
		
		loan.setBook(book);
		loan.setUser(user);
		loan.setStatus(Status.ACTIVE);
		loan.setLoanDate(loanDate);
		loan.setExpectedReturnDate(loanPolicy.calculateExpectedReturnDate(loanDate));
		
		loanRepository.save(loan);
		
		BookLoanResponseDTO response = loanMapper.toBookLoanResponseDTO(loan);
		
		return response;
	}
	
	@Transactional
	public void returnLoanBySystem() {
		
		List<Loan> expiredLoans = loanRepository.findByStatusAndExpectedReturnDateBefore(Status.ACTIVE, 
				LocalDateTime.now());
		
		expiredLoans.forEach(Loan::returnedLoanBySystem);
	}
	
	@Transactional
	public void returnLoanByUser(UUID idLoan, UUID idUsers) {
		Loan loan = loanRepository.findByIdAndUser(idLoan, idUsers)
								  .orElseThrow(() -> new LoanNotFoundException("Empréstimo não encontrado"));
		
		loan.returnedLoanByUser();
		
		loanRepository.save(loan);
	}
	
	public Page<BookLoanResponseDTO> listLoans(UUID idUser, Status status, Pageable pageable) {
		Page<Loan> listLoans = loanRepository.findByUserAndStatus(idUser, status, pageable);
		
		Page<BookLoanResponseDTO> response = listLoans.map(loanMapper::toBookLoanResponseDTO);
		
		return response;
	}
	
	public LoanSummaryResponseDTO loanSummary(UUID idUser) {
		Integer activeLoans = loanRepository.countActiveLoans(idUser);
		
		Integer totalLoans = loanRepository.countByUserIdUsers(idUser);
		
		LocalDate nextDueDate = loanRepository.findNextDueDate(idUser)
													.orElse(null);
		
		long nextDueDateDays = 0;
		
		if(nextDueDate != null) {
			nextDueDateDays = ChronoUnit.DAYS.between(LocalDate.now(), nextDueDate);
		}
		
		LoanSummaryResponseDTO response = new LoanSummaryResponseDTO(activeLoans, totalLoans, nextDueDateDays);
		
		return response;
		
	}
}
