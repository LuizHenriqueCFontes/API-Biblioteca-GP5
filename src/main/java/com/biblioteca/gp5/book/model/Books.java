package com.biblioteca.gp5.book.model;

import java.time.LocalDateTime;
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
public class Books {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_books")
	private UUID idBook;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String author;

	private String description;
	
	@Column(name = "cover_url")
	private String coverUrl;
	
	@Column(name = "total_quantity")
	private Integer totalQuantity = 0;
	
	@Column(name = "available_quantity")
	private Integer availableQuantity = 0;

	private boolean active = true;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

}
