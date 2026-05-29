package com.biblioteca.gp5.bookcategories.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.category.model.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "book_categories")
@Data
public class BookCategories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_book_categories")
	private UUID idBookCategories;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_books")
	private Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categories")
	private Category category;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	public BookCategories(Book book, Category category) {
		this.book = book;
		this.category = category;
	}

}
