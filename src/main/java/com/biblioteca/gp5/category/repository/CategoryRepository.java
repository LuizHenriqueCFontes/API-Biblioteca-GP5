package com.biblioteca.gp5.category.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.category.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
	boolean existsByName(String name);
	
	@Query("""
		SELECT new com.biblioteca.gp5.category.dto.response.CategoryResponseDTO(
			c.idCategory,
			c.name,
			COUNT(bc)				
		)
		FROM Category c
		LEFT JOIN c.bookCategories bc
		WHERE(
			:name IS NULL
			OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))			
		)
		GROUP BY c.idCategory, c.name
	""")
	Page<CategoryResponseDTO> search(@Param("name") String name, Pageable pageable);
	
	
	@Query("""
		SELECT new com.biblioteca.gp5.category.dto.response.CategoryResponseDTO(
			c.idCategory,
			c.name,
			COUNT(bc)				
		)
		FROM Category c
		LEFT JOIN c.bookCategories bc
		WHERE(
			:name IS NULL
			OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))			
		)
		GROUP BY c.idCategory, c.name
	""")
	List<CategoryResponseDTO> findCategories(@Param("name") String name);

}
