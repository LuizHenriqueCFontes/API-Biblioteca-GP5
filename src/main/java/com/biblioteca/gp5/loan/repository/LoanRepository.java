package com.biblioteca.gp5.loan.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.loan.model.Loan;
import com.biblioteca.gp5.loan.model.enums.Status;
import com.biblioteca.gp5.user.model.User;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID>{
	
	boolean existsByUserAndBookAndStatus(User user, Book book, Status status);
	
	List<Loan> findByStatusAndExpectedReturnDateBefore(Status status, LocalDateTime date);
	
	@Query("""
		SELECT l
		FROM Loan l
		WHERE l.idLoan = :idLoan
		  AND l.user.idUsers = :idUsers
			
	""")
	Optional<Loan> findByIdAndUser(@Param("idLoan") UUID idLoan, 
								  @Param ("idUsers") UUID idUsers);
	
	@Query("""
		SELECT l
		FROM Loan l
		WHERE l.user.idUsers = :userId
			AND l.status = :status
	"""
	)
	Page<Loan> findByUserAndStatus(@Param("userId") UUID userId, @Param("status") Status status, Pageable pageable);
	
	Integer countByUserIdUsers(UUID idUser);
	
	@Query("""
		SELECT COUNT(l)
		FROM Loan l
		WHERE l.user.idUsers = :idUser
			AND l.status = 'ACTIVE' 
	"""
	)
	Integer countActiveLoans(@Param("idUser") UUID idUser);
	
	@Query("""
		SELECT MIN(l.expectedReturnDate)
		FROM Loan l
		WHERE l.user.idUsers = :idUser
			AND l.status = 'ACTIVE'
	"""
	)
	Optional<LocalDate> findNextDueDate(@Param("idUser") UUID idUser);
	
	
	
}
