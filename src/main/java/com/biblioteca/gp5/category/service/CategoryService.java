package com.biblioteca.gp5.category.service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.gp5.bookcategories.repository.BookCategoriesRepository;
import com.biblioteca.gp5.category.dto.request.CreateCategoryRequestDTO;
import com.biblioteca.gp5.category.dto.request.EditCategoryRequestDTO;
import com.biblioteca.gp5.category.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.ListCategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.EditCategoryResponseDTO;
import com.biblioteca.gp5.category.mapper.CategoryMapper;
import com.biblioteca.gp5.category.model.Category;
import com.biblioteca.gp5.category.repository.CategoryRepository;
import com.biblioteca.gp5.exception.category.CategoryAlreadCadastredException;
import com.biblioteca.gp5.exception.category.CategoryInUseException;
import com.biblioteca.gp5.exception.category.CategoryNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	private final BookCategoriesRepository bookCategoriesRepository;
	
	public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, 
			BookCategoriesRepository bookCategoriesRepository) {
		this.categoryRepository = categoryRepository; 
		this.categoryMapper = categoryMapper;
		this.bookCategoriesRepository = bookCategoriesRepository;
	}
	
	public ListCategoryResponseDTO createCategory(CreateCategoryRequestDTO request) {
	
		String normalizedName = request.name()
										.trim()
										.toLowerCase(Locale.ROOT);
		
		if(categoryRepository.existsByName(normalizedName)) {
			throw new CategoryAlreadCadastredException("Categoria já cadastrada");
		}
		
		Category category = new Category();
		
		category.setName(normalizedName);
		
		categoryRepository.save(category);
		
		ListCategoryResponseDTO response = categoryMapper.toCreateCategoryResponseDTO(category);
		
		return response;
	}
	
	public Page<CategoryResponseDTO> searchCategories(String name, Pageable pageable){
		if(name != null && name.isBlank()) {
			name = null;
		}
		
		Page<CategoryResponseDTO> response = categoryRepository.search(name, pageable);
		
		return response;
	}
	
	public List<CategoryResponseDTO> listCategories(String name, Integer limit) {
		Limit resultLimit;
		
		if(limit != null) {
			resultLimit = Limit.of(limit);
			
		}else {
			resultLimit = Limit.unlimited();
		}
		
		List<CategoryResponseDTO> response = categoryRepository.findCategories(name, resultLimit);
		
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
	
	@Transactional
	public void deleteCategories(UUID idCategory) {
		Category category = categoryRepository.findById(idCategory)
											  .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
		
		boolean existsRelation = bookCategoriesRepository.existsByCategoryIdCategory(category.getIdCategory());
		
		if(existsRelation) {
			throw new CategoryInUseException("Categoria em uso");
		}
		
		categoryRepository.delete(category);												
	}
	
	

}
