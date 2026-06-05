package com.biblioteca.gp5.bookcategories.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.bookcategories.model.BookCategories;

@Repository
public interface BookCategoriesRepository extends JpaRepository<BookCategories, UUID> {
	
	//flushAutomatically: Enviar o que tem na memoria hibernate pendente para db, para nao deixar sincronizado
	/*clearAutomatically: Apagar os dados antigos da memoria do hibernate, para quando realizar a exclusão, 
	nao ter o objeto deletado na memoria dele*/
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("""
		DELETE FROM BookCategories bc
		WHERE bc.book.idBook = :idBook	
			"""
	)
	void deleteAllByIdBook(@Param("idBook") UUID idBook);
}
