package com.biblioteca.gp5.loan.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.loan.model.Loan;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.user.model.User;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID>{
	
	boolean existsByUserAndBookAndStatus(User user, Book book, Status status);
	
	List<Loan> findByStatusAndExpirationDateBefore(Status status, LocalDateTime dadte);

}
