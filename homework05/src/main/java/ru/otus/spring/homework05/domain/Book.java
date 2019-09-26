package ru.otus.spring.homework05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {
    private long id;

    @NonNull
    private String bookName;

    @NonNull
    private Author author;

    @NonNull
    private Genre genre;
}
