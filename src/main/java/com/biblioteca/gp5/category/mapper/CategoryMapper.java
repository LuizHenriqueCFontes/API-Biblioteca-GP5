package com.biblioteca.gp5.category.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.biblioteca.gp5.category.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.CreateCategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.EditCategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.ListCategoriesResponseDTO;
import com.biblioteca.gp5.category.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	CreateCategoryResponseDTO toCreateCategoryResponseDTO(Category category);
	
	ListCategoriesResponseDTO toListCategoriesResponseDTO(Category category);
	
	EditCategoryResponseDTO toEditCategoryResponseDTO(Category category);
	
	List<CategoryResponseDTO> toListCategoryResponseDTO(List<Category> category);
	

}
