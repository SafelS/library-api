package com.library.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class BookResponseDto {

    private Long bookId;
    private Long authorId;
    private String title;
    private String authorName;
    private int publicationYear;
    private String isbn;
    private List<CategoryResponseDto> categories;


}
