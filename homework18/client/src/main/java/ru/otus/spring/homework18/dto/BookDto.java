package ru.otus.spring.homework18.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String author;
    private String name;
    private String genre;
    private boolean isadult;

}
