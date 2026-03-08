package com.library.service;


import com.library.dto.CategoryRequestDto;
import com.library.dto.CategoryResponseDto;
import com.library.model.Category;
import com.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
        return new CategoryResponseDto(category.getId(), category.getName());
    }
}
