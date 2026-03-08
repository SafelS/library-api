package com.library.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BookResponseDto {

    private Long bookId;
    private Long authorId;
    private String title;
    private String authorName;
    private int publicationDate;
    private String isbn;


}
