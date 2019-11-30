package ru.otus.spring.homework12.service;

import ru.otus.spring.homework12.domain.Author;

import java.util.List;

public interface AuthorService {
    String insert(Author author);

    List<Author> showAllRows();

    Author showById(long id);

    String deleteById(long id);

}
