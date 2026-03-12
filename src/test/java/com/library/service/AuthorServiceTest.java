package com.library.service;

import com.library.dto.AuthorRequestDto;
import com.library.dto.AuthorResponseDto;
import com.library.exception.ResourceNotFoundException;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;


    @Test
    void shouldCreateAuthor(){
        //Arrange
        AuthorRequestDto requestDto = new AuthorRequestDto();
        requestDto.setName("J.K. Rowling");
        requestDto.setBio("Wrote Harry Potter");

        Author savedAuthor = new Author();
        savedAuthor.setId(1L);
        savedAuthor.setName("J.K. Rowling");
        savedAuthor.setBio("Wrote Harry Potter");

        when(authorRepository.save(any())).thenReturn(savedAuthor);

        //Act
        AuthorResponseDto responseDto = authorService.createAuthor(requestDto);

        //Assert
        assertEquals("J.K. Rowling", responseDto.getName());
        assertEquals("Wrote Harry Potter", responseDto.getBio());
        assertNotNull(responseDto.getId());

    }

    @Test
    void shouldGetAuthorById(){

        Author author = new Author();
        author.setId(1L);
        author.setName("J.K. Rowling");
        author.setBio("Wrote Harry Potter");

        when(authorRepository.findById(any())).thenReturn(Optional.of(author));

        AuthorResponseDto responseDto = authorService.getAuthorById(1L);

        assertEquals("J.K. Rowling", responseDto.getName());
        assertEquals("Wrote Harry Potter", responseDto.getBio());
        //assertNotNull(responseDto.getId());
        assertEquals(author.getId(), responseDto.getId());


    }

    @Test
    void shouldThrowExceptionWhenAuthorNotFound(){

        when(authorRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()->{
            authorService.getAuthorById(1L);
        });

    }
}
