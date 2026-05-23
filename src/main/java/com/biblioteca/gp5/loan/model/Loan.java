package com.biblioteca.gp5.loan.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "loans")
@Data
public class Loan {
	
	@Id
	@Column(name = "id_loans")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idLoan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	// FetchType.LAZY evita carregar relacionamentos automaticamente.
	// Ao buscar um Loan, apenas a entidade principal é carregada inicialmente,
	// evitando queries grandes e melhorando a performance.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name = "loan_date")
	private LocalDateTime loanDate;
	
	@Column(name = "expected_return_date", nullable = false)
	private LocalDateTime expectedReturnDate;
	
	@Column(name = "actual_return_date")
	private LocalDateTime actualReturnDate;
	
	
	public void expiresLoan() {
		
		if(this.status != Status.ACTIVE) {
			return;
		}
		
		this.status = status.EXPIRED;		
	}

}
