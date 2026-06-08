package com.biblioteca.gp5.bookcategories.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.biblioteca.gp5.category.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.model.Category;

@Mapper(componentModel = "spring")
public interface BookCategoriesMapper {
	
	List<CategoryResponseDTO> toListCategoryResponseDTO(List<Category> category);

}
