package com.biblioteca.gp5.reading.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.biblioteca.gp5.book.model.Book;
import com.biblioteca.gp5.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "reading", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"book_id", "user_id"})
})
@Data
public class Reading {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_reading")
	private UUID idReading;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "epub_cfi")
	private String epubCfi;
	
	@Column(precision = 5, scale = 2)
	private BigDecimal percentage;
	
	@Column(name = "last_reading")
	private LocalDateTime lastReading;
}

