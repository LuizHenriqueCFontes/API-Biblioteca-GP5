package com.biblioteca.gp5.bookcategory.model;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.category.model.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "book_categorioes")
@Data
public class BookCategory {
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_books")
	private Book book;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categories")
	private Category category;

}
