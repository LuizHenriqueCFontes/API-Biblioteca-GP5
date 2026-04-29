package com.biblioteca.gp5.book.mapper;

import org.mapstruct.Mapper;

import com.biblioteca.gp5.book.dto.response.BookListResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexAuthorResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexBookResponseDTO;

@Mapper(componentModel = "spring")
public interface BookMapper {
	
	BookListResponseDTO toBookListResponseDTO(GutendexBookResponseDTO book);
	
	default String extractAuthorName(GutendexAuthorResponseDTO author) {
		return author.name();
	}
}
