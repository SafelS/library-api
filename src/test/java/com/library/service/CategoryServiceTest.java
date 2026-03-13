package com.library.service;


import com.library.dto.CategoryRequestDto;
import com.library.dto.CategoryResponseDto;
import com.library.exception.ResourceNotFoundException;
import com.library.model.Category;
import com.library.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;


    @Test
    void shouldCreateCategory() {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName("Fantasy");

        Category savedCategory = new Category();
        savedCategory.setId(1L);
        savedCategory.setName("Fantasy");

        when(categoryRepository.save(any())).thenReturn(savedCategory);


        CategoryResponseDto categoryResponseDto = categoryService.createCategory(requestDto);


        assertEquals(requestDto.getName(), categoryResponseDto.getName());
        assertNotNull(categoryResponseDto.getId());

    }

    @Test
    void shouldGetCategoryById(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Fantasy");

        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));


        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(1L);

        assertEquals(category.getName(), categoryResponseDto.getName());
        assertEquals(category.getId(), categoryResponseDto.getId());

    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound(){

        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.getCategoryById(999L);
        });

    }

    @Test
    void shouldUpdateCategory(){

        CategoryRequestDto  requestDto = new CategoryRequestDto();
        requestDto.setName("Adventure");

        Category category = new Category();
        category.setId(1L);
        category.setName("Fantasy");

        Category updatedCategory = new Category();
        updatedCategory.setId(1L);
        updatedCategory.setName("Adventure");

        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(updatedCategory);


        CategoryResponseDto responseDto = categoryService.updateCategory(category.getId(), requestDto);

        assertEquals(requestDto.getName(), responseDto.getName());
        assertEquals(category.getId(), responseDto.getId());


    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFoundOnUpdate(){

        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.getCategoryById(999L);
        });

    }

    @Test
    void shouldDeleteCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Fantasy");

        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));

        categoryService.deleteCategory(1L);

        verify(categoryRepository).delete(category);
    }

    @Test
    void shouldGetAllCategories(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Fantasy");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Adventure");

        when(categoryRepository.findAll()).thenReturn(List.of(category, category2));

        List<CategoryResponseDto> responseDto = categoryService.getAllCategories();

        assertEquals(responseDto.get(0).getName(), category.getName());
        assertEquals(responseDto.get(1).getName(), category2.getName());

    }

}
