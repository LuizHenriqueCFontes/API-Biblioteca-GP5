package com.biblioteca.gp5.category.service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.bookcategories.repository.BookCategoriesRepository;
import com.biblioteca.gp5.category.dto.request.CreateCategoryRequestDTO;
import com.biblioteca.gp5.category.dto.request.DeleteCategoriesRequestDTO;
import com.biblioteca.gp5.category.dto.request.EditCategoryRequestDTO;
import com.biblioteca.gp5.category.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.CreateCategoryResponseDTO;
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
	
	public List<CategoryResponseDTO> listCategories(String name){
		List<Category> category;
		
		if(name == null || name.isBlank()) {
				category = categoryRepository.findAll();
		}else {
			category = categoryRepository.findByNameContainingIgnoreCase(name);
		}
		
		List<CategoryResponseDTO> response = categoryMapper.toListCategoryResponseDTO(category);
		
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
	public void deleteCategories(DeleteCategoriesRequestDTO request) {
		List<Category> categories = categoryRepository.findAllById(request.idCategories());
		
		if(categories.size() != request.idCategories().size()) {
			throw new CategoryNotFoundException("Uma ou mais categorias não encontradas");
		}
		
		for(Category category : categories) {
			
			boolean existsRelation = bookCategoriesRepository.existsByCategoryIdCategory(category.getIdCategory());
			
			if(existsRelation) {
				throw new CategoryInUseException("Categoria em uso");
			}
		}
		
		categoryRepository.deleteAll(categories);
														
	}
	
	

}
