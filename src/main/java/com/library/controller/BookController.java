package com.library.controller;


import com.library.dto.BookRequestDto;
import com.library.dto.BookResponseDto;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor

public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto createBook(@RequestBody BookRequestDto request){
        return bookService.createBook(request);
    }

    @GetMapping("/{id}")
    public BookResponseDto getBook(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<BookResponseDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long id, @RequestBody BookRequestDto request){
        return bookService.updateBook(id, request);
    }
}
