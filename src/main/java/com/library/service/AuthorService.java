package com.library.service;


import com.library.dto.AuthorRequestDto;
import com.library.dto.AuthorResponseDto;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author Not Found"));
        return new AuthorResponseDto(author.getId(), author.getName(), author.getBio());
    }
}
