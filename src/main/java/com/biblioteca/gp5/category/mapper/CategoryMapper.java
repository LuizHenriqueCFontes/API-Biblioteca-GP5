package com.biblioteca.gp5.category.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.biblioteca.gp5.category.dto.response.ListCategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.EditCategoryResponseDTO;
import com.biblioteca.gp5.category.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	ListCategoryResponseDTO toCreateCategoryResponseDTO(Category category);
	
	//ListCategoriesResponseDTO toListCategoriesResponseDTO(Category category);
	
	EditCategoryResponseDTO toEditCategoryResponseDTO(Category category);
	
	List<ListCategoryResponseDTO> toListCategoryResponseDTO(List<Category> category);
	

}
