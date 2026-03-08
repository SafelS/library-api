package com.library.dto;


import lombok.Data;


@Data
public class BookRequestDto {

    private String title;
    private String isbn;
    private int publicationDate;
    private Long authorId;

}
