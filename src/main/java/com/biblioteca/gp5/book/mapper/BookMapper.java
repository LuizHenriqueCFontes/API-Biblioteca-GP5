package com.biblioteca.gp5.book.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.book.dto.response.BookDetailsResponseDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.dto.response.EditBookResponseDTO;
import com.biblioteca.gp5.book.enums.BookCover;
import com.biblioteca.gp5.book.enums.BookFormat;
import com.biblioteca.gp5.book.enums.BookSources;
import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.bookcategories.model.BookCategories;
import com.biblioteca.gp5.category.mapper.CategoryMapper;
import com.biblioteca.gp5.category.model.Category;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexAuthorResponseDTO;
import com.biblioteca.gp5.integration.gutendex.dto.response.GutendexBookResponseDTO;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface BookMapper {
	
	//Source de onde o valor vem
	//Target para onde o valor vai
	//Mapping ensinar como ele deve converter
	@Mapping(source = "id", target = "gutenbergId")
	@Mapping(expression = "java(extractCoverUrl(gutendexBook))", target = "coverUrl")
	@Mapping(expression = "java(extractFileUrl(gutendexBook))", target = "fileUrl")
	@Mapping(expression = "java(extractSource())", target = "source")
	//@Mapping(expression = "java(extractName(gutendexAuthor))", target = "authors")
	Book toEntity(GutendexBookResponseDTO gutendexBook);
	
	@Mapping(source = "idBook", target = "id")
	BookResponseDTO toBookResponseDTO(Book book);
	
	EditBookResponseDTO toEditBookResponseDTO(Book book);
	
	@Mapping(source = "idBook", target = "id")
	@Mapping(source = "bookCategories", target = "categories")
	BookDetailsResponseDTO toBookDetailsResponseDTO(Book book);
	
	default List<Category> mapCategories(List<BookCategories> bookCategories) {
		return bookCategories.stream()
							.map(BookCategories::getCategory)
							.toList();
	}
	
	default String extractName(GutendexAuthorResponseDTO gutendexAuthor) {
		return gutendexAuthor.name();
	}
	
	default String extractCoverUrl(GutendexBookResponseDTO gutendexBook) {
		return gutendexBook.formats().get(BookCover.IMAGE.getValue());
	}
	
	default String extractFileUrl(GutendexBookResponseDTO gutendexBook) {
		return gutendexBook.formats().get(BookFormat.EPUB.getValue());
	}
	
	default String extractSource() {
		return BookSources.GUTENDEX.getValue();
	}

	
}
