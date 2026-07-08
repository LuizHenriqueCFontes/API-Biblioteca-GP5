package com.biblioteca.gp5.category.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.biblioteca.gp5.bookcategories.model.BookCategories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {
	
	@Id
	@Column(name = "id_categories")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idCategory;
	
	@Column(nullable = false)
	private String name;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "category")
	private Set<BookCategories> bookCategories = new HashSet<>();
}
