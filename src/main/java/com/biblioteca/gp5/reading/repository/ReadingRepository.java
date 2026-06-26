package com.biblioteca.gp5.reading.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.gp5.reading.model.Reading;

public interface ReadingRepository extends JpaRepository<Reading, UUID> {
	
	@Query("""
			SELECT 
				CASE
					WHEN COUNT(r) > 0 THEN true ELSE false
				END	
			FROM Reading r
			WHERE r.user.idUsers = :idUser
			  AND r.book.idBook = :idBook
			
	"""
	)
	boolean existsByUserAndBook(@Param("idUser") UUID idUser, @Param("idBook") UUID idBook);
	
	@Query("""
			SELECT r
			FROM Reading r
			WHERE r.user.idUsers = :idUser
				AND r.book.idBook = :idBook			
	"""
	)
	Optional<Reading> findByUserAndBook(@Param("idUser") UUID idUser, @Param("idBook") UUID idBook);

}
