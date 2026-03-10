package com.library.controller;

import com.library.dto.CategoryRequestDto;
import com.library.dto.CategoryResponseDto;
import com.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto request){
        return categoryService.createCategory(request);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<CategoryResponseDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategoryById( @PathVariable Long id, @RequestBody CategoryRequestDto request){
        return categoryService.updateCategory(id, request);
    }

}
