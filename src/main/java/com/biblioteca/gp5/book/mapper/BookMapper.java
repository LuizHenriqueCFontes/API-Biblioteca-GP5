package com.biblioteca.gp5.book.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.gp5.book.dto.response.BookDetailsResponseDTO;
import com.biblioteca.gp5.book.dto.response.BookResponseDTO;
import com.biblioteca.gp5.book.dto.response.BooksRecentlyCreatedResponseDTO;
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
import com.biblioteca.gp5.shared.mapper.FileUrlMapper;


@Mapper(componentModel = "spring", 
uses = { CategoryMapper.class, FileUrlMapper.class }
)
public abstract class BookMapper {
	
	//Source de onde o valor vem
	//Target para onde o valor vai
	//Mapping ensinar como ele deve converter
	@Mapping(source = "id", target = "gutenbergId")
	@Mapping(expression = "java(extractCoverUrl(gutendexBook))", target = "coverUrl")
	@Mapping(expression = "java(extractFileUrl(gutendexBook))", target = "fileUrl")
	@Mapping(expression = "java(extractSource())", target = "source")
	@Mapping(source = "summaries", target = "description")
	//@Mapping(expression = "java(extractName(gutendexAuthor))", target = "authors")
	public abstract Book toEntity(GutendexBookResponseDTO gutendexBook);
	
	@Mapping(source = "idBook", target = "id")
	@Mapping(qualifiedByName = "toFileUrl", target = "coverUrl")
	@Mapping(qualifiedByName = "toFileUrl", target = "fileUrl")
	public abstract BookResponseDTO toBookResponseDTO(Book book);
	
	public abstract EditBookResponseDTO toEditBookResponseDTO(Book book);
	
	@Mapping(source = "idBook", target = "id")
	@Mapping(source = "bookCategories", target = "categories")
	@Mapping(qualifiedByName = "toFileUrl", target = "coverUrl")
	@Mapping(qualifiedByName = "toFileUrl", target = "fileUrl")
	public abstract BookDetailsResponseDTO toBookDetailsResponseDTO(Book book);
	
	public abstract BooksRecentlyCreatedResponseDTO toBooksRecentlyCreatedResponseDTO(Book book);
	
	public abstract List<BooksRecentlyCreatedResponseDTO> toBooksRecentlyCreatedResponseDTO(List<Book> books);
	
	protected List<Category> mapCategories(List<BookCategories> bookCategories) {
		return bookCategories.stream()
							.map(BookCategories::getCategory)
							.toList();
	}
	
	protected String extractName(GutendexAuthorResponseDTO gutendexAuthor) {
		return gutendexAuthor.name();
	}
	
	protected String extractCoverUrl(GutendexBookResponseDTO gutendexBook) {
		return gutendexBook.formats().get(BookCover.IMAGE.getValue());
	}
	
	protected String extractFileUrl(GutendexBookResponseDTO gutendexBook) {
		return gutendexBook.formats().get(BookFormat.EPUB.getValue());
	}
	
	protected String extractSource() {
		return BookSources.GUTENDEX.getValue();
	}

	
}
