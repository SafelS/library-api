package com.library.service;


import com.library.dto.BookRequestDto;
import com.library.dto.BookResponseDto;
import com.library.model.Author;
import com.library.model.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookResponseDto createBook(BookRequestDto requestDto) {
        Author author = authorRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author Not Found"));

        Book book = new Book();
        book.setTitle(requestDto.getTitle());
        book.setAuthor(author);
        book.setPublicationYear(requestDto.getPublicationYear());
        book.setIsbn(requestDto.getIsbn());

        //TODO Update BookService to match updated BookRequestDTO & BookResponseDTO
        Book savedBook = bookRepository.save(book);
        return new BookResponseDto(savedBook.getId(), author.getId(),savedBook.getTitle(), author.getName(), savedBook.getPublicationYear(), savedBook.getIsbn());

    }

    public BookResponseDto getBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
        return new BookResponseDto(book.getId(), book.getAuthor().getId(),
                book.getTitle(), book.getAuthor().getName(), book.getPublicationYear(), book.getIsbn());
    }


}
