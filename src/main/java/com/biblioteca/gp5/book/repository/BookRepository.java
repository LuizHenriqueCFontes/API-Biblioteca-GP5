package com.biblioteca.gp5.book.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
	Page<Book> findByTitleContainingIgnoreCaseAndActiveTrue(String title, Pageable pageable);
	
	Page<Book> findAll(Pageable pageable);
	
	Page<Book> findByActiveTrue(Pageable pageable);
	
	@Query("""
		SELECT b
		FROM Book b
		JOIN b.bookCategories bc
		WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))
		AND bc.category.idCategory IN :idsCategories
			AND b.active = TRUE
			"""
	)
	Page<Book> findByTitleAndCategoriesAndActive(@Param("title") String title, @Param("idsCategories") List<UUID> 
	idsCategories, Pageable pageable);
	
	@Query("""
		SELECT DISTINCT b
		FROM Book b
		JOIN b.bookCategories bc
		WHERE bc.category.idCategory IN :idsCategories
			AND b.active = TRUE
			"""
	)
	Page<Book> findByCategoriesAndActive(@Param("idsCategories") List<UUID> idsCategories, Pageable pageable);
	
	boolean existsByGutenbergIdAndActiveTrue(Integer id);
	
	boolean existsByGutenbergIdAndActiveFalse(Integer id);
	
	Book findByGutenbergId(Integer id);
	
	List<Book> findTop5ByOrderByCreationDateDesc();

}