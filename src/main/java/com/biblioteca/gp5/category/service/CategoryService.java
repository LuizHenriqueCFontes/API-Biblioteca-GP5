package com.biblioteca.gp5.category.service;

import java.util.Locale;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.category.dto.request.CreateCategoryRequestDTO;
import com.biblioteca.gp5.category.dto.request.EditCategoryRequestDTO;
import com.biblioteca.gp5.category.dto.response.CreateCategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.EditCategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.ListCategoriesResponseDTO;
import com.biblioteca.gp5.category.mapper.CategoryMapper;
import com.biblioteca.gp5.category.model.Category;
import com.biblioteca.gp5.category.repository.CategoryRepository;
import com.biblioteca.gp5.exception.category.CategoryAlreadCadastredException;
import com.biblioteca.gp5.exception.category.CategoryNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	
	public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository; 
		this.categoryMapper = categoryMapper;
	}
	
	public CreateCategoryResponseDTO createCategory(CreateCategoryRequestDTO request) {
	
		String normalizedName = request.name()
										.trim()
										.toLowerCase(Locale.ROOT);
		
		if(categoryRepository.existsByName(normalizedName)) {
			throw new CategoryAlreadCadastredException("Categoria já cadastrada");
		}
		
		Category category = new Category();
		
		category.setName(normalizedName);
		
		categoryRepository.save(category);
		
		CreateCategoryResponseDTO response = categoryMapper.toCreateCategoryResponseDTO(category);
		
		return response;
	}
	
	public Page<ListCategoriesResponseDTO> listCategories(String name, Pageable pageable){
		Page<Category> category;
		
		if(name == null || name.isBlank()) {
				category = categoryRepository.findAll(pageable);
		}else {
			category = categoryRepository.findByNameContainingIgnoreCase(name, pageable);
		}
		
		Page<ListCategoriesResponseDTO> response = category.map(categoryMapper::toListCategoriesResponseDTO);
		
		return response;
	}
	
	@Transactional
	public EditCategoryResponseDTO EditCategory(UUID idCategory, EditCategoryRequestDTO request) {
		Category category = categoryRepository.findById(idCategory)
												.orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
		
		
		String editNameNormalized = request.name()
											.trim()
											.toLowerCase(Locale.ROOT);
		
		if(categoryRepository.existsByName(editNameNormalized)) {
			throw new CategoryAlreadCadastredException("Categoria já cadastrada");
		}
		
		category.setName(editNameNormalized);
		
		categoryRepository.save(category);
		
		EditCategoryResponseDTO response = categoryMapper.toEditCategoryResponseDTO(category);
		
		return response;
	}
	
	

}
