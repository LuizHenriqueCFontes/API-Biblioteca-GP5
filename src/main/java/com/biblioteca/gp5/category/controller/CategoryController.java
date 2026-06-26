package com.biblioteca.gp5.category.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.gp5.category.dto.request.CreateCategoryRequestDTO;
import com.biblioteca.gp5.category.dto.request.DeleteCategoriesRequestDTO;
import com.biblioteca.gp5.category.dto.request.EditCategoryRequestDTO;
import com.biblioteca.gp5.category.dto.response.CategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.ListCategoryResponseDTO;
import com.biblioteca.gp5.category.dto.response.EditCategoryResponseDTO;
import com.biblioteca.gp5.category.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping
	public ResponseEntity<ListCategoryResponseDTO> createCategory(@RequestBody @Valid CreateCategoryRequestDTO request){
		ListCategoryResponseDTO response = categoryService.createCategory(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping("/summary")
	public ResponseEntity<Page<CategoryResponseDTO>> searchCategories(@RequestParam(required = false) String name, Pageable pageable){
		
		Page<CategoryResponseDTO> response = categoryService.searchCategories(name, pageable);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ListCategoryResponseDTO>> listCategories() {
		List<ListCategoryResponseDTO> response = categoryService.listCategories();
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{idCategory}")
	public ResponseEntity<EditCategoryResponseDTO> editCategory(@PathVariable UUID idCategory, @RequestBody EditCategoryRequestDTO request){
		EditCategoryResponseDTO response = categoryService.EditCategory(idCategory, request);
		
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteCategories(@RequestBody @Valid DeleteCategoriesRequestDTO request) {
		categoryService.deleteCategories(request);
		
		return ResponseEntity.noContent().build();
	}

}
