package com.library.service;


import com.library.dto.CategoryRequestDto;
import com.library.dto.CategoryResponseDto;
import com.library.exception.ResourceNotFoundException;
import com.library.model.Category;
import com.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto createCategory(CategoryRequestDto request) {
        Category category = new Category();
        category.setName(request.getName());

        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponseDto(savedCategory.getId(), savedCategory.getName());
    }

    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        return new CategoryResponseDto(category.getId(), category.getName());
    }

    public List<CategoryResponseDto> getAllCategories() {

        return categoryRepository.findAll().stream()
                .map(c -> new CategoryResponseDto(c.getId(),c.getName())).toList();
    }

    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        category.setName(request.getName());

        Category updatedCategory = categoryRepository.save(category);

        return new CategoryResponseDto(updatedCategory.getId(), updatedCategory.getName());

    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        categoryRepository.delete(category);
    }
}
