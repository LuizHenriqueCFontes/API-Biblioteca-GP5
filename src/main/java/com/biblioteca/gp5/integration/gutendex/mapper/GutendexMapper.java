package com.biblioteca.gp5.integration.gutendex.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.book.dto.response.ImportBookResponseDTO;
import com.biblioteca.gp5.book.dto.response.ImportSearchResponseDTO;
import com.biblioteca.gp5.book.enums.BookCover;
import com.biblioteca.gp5.book.enums.BookFormat;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexAuthorResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexBookResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexSearchResponseDTO;

@Mapper(componentModel = "spring")
public interface GutendexMapper {
	
	@Mapping(source = "summaries", target = "description")
	@Mapping(expression = "java(extractCoverUrl(gutendexBook))", target = "coverUrl")
	@Mapping(expression = "java(extractFileUrl(gutendexBook))", target = "fileUrl")
	ImportBookResponseDTO toImportBookResponseDTO(GutendexBookResponseDTO gutendexBook);
	
	default String extractName(GutendexAuthorResponseDTO author) {
		return author.name();
	}
	
	default String extractCoverUrl(GutendexBookResponseDTO gutendexBook) {
		return gutendexBook.formats().get(BookCover.IMAGE.getValue());
	}
	
	default String extractFileUrl(GutendexBookResponseDTO gutendexBook) {
		return gutendexBook.formats().get(BookFormat.EPUB.getValue());
	}
	
	ImportSearchResponseDTO toImportSearchResponseDTO(GutendexSearchResponseDTO gutendexSearch);

}
