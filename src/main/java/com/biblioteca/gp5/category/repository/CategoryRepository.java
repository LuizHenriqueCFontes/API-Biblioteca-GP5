package com.biblioteca.gp5.category.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.category.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
	boolean existsByName(String name);
	
	List<Category> findByNameContainingIgnoreCase(String name);

}
