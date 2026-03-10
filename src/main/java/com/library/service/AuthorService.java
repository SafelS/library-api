package com.library.service;


import com.library.dto.AuthorRequestDto;
import com.library.dto.AuthorResponseDto;
import com.library.exception.ResourceNotFoundException;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;



    public AuthorResponseDto createAuthor(AuthorRequestDto requestDto){

        Author author = new Author();
        author.setName(requestDto.getName());
        author.setBio(requestDto.getBio());

        Author savedAuthor = authorRepository.save(author);

        return new AuthorResponseDto(savedAuthor.getId(), savedAuthor.getName(), savedAuthor.getBio());

    }

    public AuthorResponseDto getAuthorById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author Not Found"));
        return new AuthorResponseDto(author.getId(), author.getName(), author.getBio());
    }

    public List<AuthorResponseDto> getAllAuthors(){
        //List<Author> authors = authorRepository.findAll();

        return authorRepository.findAll().stream().
                map(a -> new AuthorResponseDto(a.getId(), a.getName(), a.getBio())).toList();

    }

    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto requestDto){
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author Not Found"));
        author.setName(requestDto.getName());
        author.setBio(requestDto.getBio());

        Author updatedAuthor = authorRepository.save(author);
        return new AuthorResponseDto(updatedAuthor.getId(), updatedAuthor.getName(), updatedAuthor.getBio());
    }

    public void deleteAuthor(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author Not Found"));
        authorRepository.delete(author);
    }
}
