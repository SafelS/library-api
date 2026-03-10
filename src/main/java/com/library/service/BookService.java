package com.library.service;


import com.library.dto.BookRequestDto;
import com.library.dto.BookResponseDto;
import com.library.dto.CategoryResponseDto;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Category;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;


    public BookResponseDto createBook(BookRequestDto requestDto) {
        Author author = authorRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author Not Found"));

        Book book = new Book();
        book.setTitle(requestDto.getTitle());
        book.setAuthor(author);
        book.setPublicationYear(requestDto.getPublicationYear());
        book.setIsbn(requestDto.getIsbn());


        List<Category> categories = categoryRepository.findAllById(requestDto.getCategoryId());
        book.setCategories(categories);
        Book savedBook = bookRepository.save(book);

        List<CategoryResponseDto> categoryResponseDtos = categories.stream().
                map(c -> new CategoryResponseDto(c.getId(), c.getName())).toList();

        return new BookResponseDto(savedBook.getId(), author.getId(),savedBook.getTitle(),
                author.getName(), savedBook.getPublicationYear(), savedBook.getIsbn(), categoryResponseDtos);

    }

    public BookResponseDto getBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
        //List<Category> categories = book.getCategories();

        List<CategoryResponseDto> categoryResponseDtos = book.getCategories().stream().
                map(c -> new CategoryResponseDto(c.getId(), c.getName())).toList();

        return new BookResponseDto(book.getId(), book.getAuthor().getId(),
                book.getTitle(), book.getAuthor().getName(), book.getPublicationYear(), book.getIsbn(),  categoryResponseDtos);
    }


}
