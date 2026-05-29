package com.biblioteca.gp5.bookcategories.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.biblioteca.gp5.bookcategories.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.model.Category;

@Mapper(componentModel = "spring")
public interface BookCategoriesMapper {
	
	List<CategoryResponseDTO> toListCategory(List<Category> category);

}
