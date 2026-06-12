package com.biblioteca.gp5.loan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.loan.dto.response.BookLoanResponseDTO;
import com.biblioteca.gp5.loan.model.Loan;

@Mapper(componentModel = "spring")
public interface LoanMapper {
	
	@Mapping(source = "book.title", target = "title")
	@Mapping(source = "book.coverUrl", target = "coverUrl")
	@Mapping(source = "book.authors", target = "authors")
	@Mapping(source = "book.fileUrl", target = "fileUrl")
	@Mapping(source = "book.idBook", target = "bookId")
	public BookLoanResponseDTO toBookLoanResponseDTO(Loan loan);

}
