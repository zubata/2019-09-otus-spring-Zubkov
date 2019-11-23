package ru.otus.spring.homework10.service;

import ru.otus.spring.homework10.domain.Author;

import java.util.List;

public interface AuthorService {
    Author insert(Author author);

    List<Author> showAllRows();

    Author showById(long id);

    void deleteById(long id);

}
