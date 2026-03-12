package com.library.service;


import com.library.dto.CategoryRequestDto;
import com.library.dto.CategoryResponseDto;
import com.library.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    //TODO To be continued!
    @Test
    void shouldCreateCategory() {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName("Fantasy");


    }

}
