package com.biblioteca.gp5.loan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.loan.dto.response.BookLoanResponseDTO;
import com.biblioteca.gp5.loan.model.Loan;
import com.biblioteca.gp5.shared.mapper.FileUrlMapper;

@Mapper(componentModel = "spring", uses = FileUrlMapper.class)
public interface LoanMapper {
	
	@Mapping(source = "loan.book.title", target = "title")
	@Mapping(source = "loan.book.coverUrl", target = "coverUrl", qualifiedByName = "toFileUrl")
	@Mapping(source = "loan.book.authors", target = "authors")
	@Mapping(source = "loan.book.fileUrl", target = "fileUrl", qualifiedByName = "toFileUrl")
	@Mapping(source = "loan.book.idBook", target = "bookId")
	public BookLoanResponseDTO toBookLoanResponseDTO(Loan loan, boolean hasReading);

}
