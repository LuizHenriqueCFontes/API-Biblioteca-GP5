package com.biblioteca.gp5.loan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.loan.dto.response.BookLoanResponseDTO;
import com.biblioteca.gp5.loan.model.Loan;

@Mapper(componentModel = "spring")
public interface LoanMapper {
	
	@Mapping(source = "loan.book.title", target = "title")
	@Mapping(source = "loan.book.coverUrl", target = "coverUrl")
	@Mapping(source = "loan.book.authors", target = "authors")
	@Mapping(source = "loan.book.fileUrl", target = "fileUrl")
	@Mapping(source = "loan.book.idBook", target = "bookId")
	public BookLoanResponseDTO toBookLoanResponseDTO(Loan loan, boolean hasReading);

}
