package com.biblioteca.gp5.book.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_books")
	private UUID idBook;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private List<String> authors;

	private List<String> description;
	
	@Column(name = "cover_url")
	private String coverUrl;
	
	@Column(name = "gutenberg_id", unique = true)
	private Integer gutenbergId;
	
	@Column(name = "file_url")
	private String fileUrl;
	
	@Column(nullable = false)
	private String source;
	
	@Column(name = "total_quantity")
	private Integer totalQuantity = 0;
	
	@Column(name = "available_quantity")
	private Integer availableQuantity = 0;

	private boolean active = true;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

}
