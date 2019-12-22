package ru.otus.spring.homework15.service;

import ru.otus.spring.homework15.domain.Author;

import java.util.List;

public interface AuthorService {

    Author insert(Author author);

    List<Author> showAllRows();

    Author showById(long id);

    String deleteById(long id);

}