package com.library.dto;


import lombok.Data;

import java.util.List;


@Data
public class BookRequestDto {

    private String title;
    private String isbn;
    private int publicationYear;
    private Long authorId;
    private List<Long> categoryId;

}
