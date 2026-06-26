package com.biblioteca.gp5.reading.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.book.repository.BookRepository;
import com.biblioteca.gp5.exception.book.BookNotFoundException;
import com.biblioteca.gp5.exception.loan.LoanNotFoundException;
import com.biblioteca.gp5.exception.reading.ReadingAlreadyStartedException;
import com.biblioteca.gp5.exception.reading.ReadingNotFoundException;
import com.biblioteca.gp5.exception.user.UserNotFoundException;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.loan.repository.LoanRepository;
import com.biblioteca.gp5.reading.dto.request.UpdateReadingRequestDTO;
import com.biblioteca.gp5.reading.dto.response.ReadingResponseDTO;
import com.biblioteca.gp5.reading.dto.response.UpdateReadingResponseDTO;
import com.biblioteca.gp5.reading.mapper.ReadingMapper;
import com.biblioteca.gp5.reading.model.Reading;
import com.biblioteca.gp5.reading.repository.ReadingRepository;
import com.biblioteca.gp5.user.model.User;
import com.biblioteca.gp5.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ReadingService {
	
	private final ReadingRepository readingRepository;
	private final LoanRepository loanRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	private final ReadingMapper readingMapper;
	
	public ReadingService(ReadingRepository readingRepository, LoanRepository loanRepository, UserRepository userRepository, 
			BookRepository bookRepository, ReadingMapper readingMapper) {
		this.readingRepository = readingRepository;
		this.loanRepository = loanRepository;
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
		this.readingMapper = readingMapper;
	}
	
	public ReadingResponseDTO startReading(UUID idUser, UUID idBook) {
		
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
		
		reading.setUser(user);
		reading.setBook(book);
		reading.setEpubCfi(null);
		reading.setPercentage(BigDecimal.ZERO);
		reading.setLastReading(LocalDateTime.now());
		
		readingRepository.save(reading);
		
		ReadingResponseDTO response = readingMapper.toReadingResponseDTO(reading);
		
		return response;
	}
	
	@Transactional
	public UpdateReadingResponseDTO updateReading(UUID idUser, UUID idBook, UpdateReadingRequestDTO request) {
		Reading reading = readingRepository.findByUserAndBook(idUser, idBook)
											.orElseThrow(() -> new ReadingNotFoundException("Leitura não encontrada"));
		
		if(request.epubCfi() != null && !request.epubCfi().isBlank()) {
			reading.setEpubCfi(request.epubCfi());
		}
		
		if(request.percentage() != null) {
			reading.setPercentage(request.percentage());
		}
		
		reading.setLastReading(LocalDateTime.now());
		
		readingRepository.save(reading);
		
		UpdateReadingResponseDTO response = readingMapper.toReadingUpdateResponseDTO(reading);
		
		return response;
	}
	
	public ReadingResponseDTO getReadingProgress(UUID idUser, UUID idBook) {
		
		Reading reading = readingRepository.findByUserAndBook(idUser, idBook)
										   .orElseThrow(() -> new ReadingNotFoundException("Leitura não encontrada"));
		
		ReadingResponseDTO response = readingMapper.toReadingResponseDTO(reading);
		
		return response;							
	}

}
