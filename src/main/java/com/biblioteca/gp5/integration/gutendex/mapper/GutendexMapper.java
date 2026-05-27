package com.biblioteca.gp5.integration.gutendex.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.book.dto.response.ImportBookResponseDTO;
import com.biblioteca.gp5.book.enums.BookCover;
import com.biblioteca.gp5.book.enums.BookFormat;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexAuthorResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexBookResponseDTO;

@Mapper(componentModel = "spring")
public interface GutendexMapper {
	
	@Mapping(expression = "java(extractSummaries(gutendexBook))", target = "description")
	@Mapping(expression = "java(extractCoverUrl(gutendexBook))", target = "coverUrl")
	@Mapping(expression = "java(extractFileUrl(gutendexBook))", target = "fileUrl")
	@Mapping(expression = "java(extractName(author))", target = "authors")
	ImportBookResponseDTO toImportBookResponseDTO(GutendexBookResponseDTO gutendexBook);
	
	default String extractSummaries(GutendexBookResponseDTO gutendexBook) {
		return String.join(" ", gutendexBook.summaries());
	}
	
	default String extractName(GutendexAuthorResponseDTO author) {
		return author.name();
	}
	
	default String extractCoverUrl(GutendexBookResponseDTO gutendexBook) {
		return gutendexBook.formats().get(BookCover.IMAGE.getValue());
	}
	
	default String extractFileUrl(GutendexBookResponseDTO gutendexBook) {
		return gutendexBook.formats().get(BookFormat.EPUB.getValue());
	}
}
