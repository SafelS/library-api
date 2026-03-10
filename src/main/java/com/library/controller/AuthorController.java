package com.library.controller;


import com.library.dto.AuthorRequestDto;
import com.library.dto.AuthorResponseDto;
import com.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponseDto createAuthor(@RequestBody AuthorRequestDto request){
        return authorService.createAuthor(request);
    }

    @GetMapping("/{id}")
    public AuthorResponseDto getAuthor(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @GetMapping
    public List<AuthorResponseDto> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PutMapping("/{id}")
    public AuthorResponseDto updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDto request){
        return authorService.updateAuthor(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
    }

}
