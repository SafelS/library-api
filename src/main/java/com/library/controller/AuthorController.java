package com.library.controller;


import com.library.dto.AuthorRequestDto;
import com.library.dto.AuthorResponseDto;
import com.library.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
