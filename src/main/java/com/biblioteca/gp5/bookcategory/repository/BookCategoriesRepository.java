package com.biblioteca.gp5.bookcategory.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.gp5.bookcategory.model.BookCategories;

@Repository
public interface BookCategoriesRepository extends JpaRepository<BookCategories, UUID> {

}
