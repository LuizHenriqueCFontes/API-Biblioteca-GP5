package com.biblioteca.gp5.loan.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.loan.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID>{

}
