package com.biblioteca.gp5.reading.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.exception.loan.LoanNotFoundException;
import com.biblioteca.gp5.exception.reading.ReadingAlreadyStartedException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.loan.repository.LoanRepository;
import com.biblioteca.gp5.reading.model.Reading;
import com.biblioteca.gp5.reading.repository.ReadingRepository;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;

@Service
public class ReadingService {
	
	private final ReadingRepository readingRepository;
	private final LoanRepository loanRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	
	public ReadingService(ReadingRepository readingRepository, LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
		this.readingRepository = readingRepository;
		this.loanRepository = loanRepository;
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}
	
	public void startReading(UUID idUser, UUID idBook) {
		
		User user = userRepository.findById(idUser)
								.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		Book book = bookRepository.findById(idBook)
								.orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));
		
		boolean hasActiveLoan = loanRepository.existsByUserAndBookAndStatus(user, book, Status.ACTIVE);
		
		if(!hasActiveLoan) {
			throw new LoanNotFoundException("Nenhum empréstimo encontrado");
		}
		
		boolean readingExists = readingRepository.existsByUserAndBook(idUser, idBook);
		
		if(readingExists) {
			throw new ReadingAlreadyStartedException("Leitura já iniciada");
		}
		
		Reading reading = new Reading();
		
		
		
	}

}
