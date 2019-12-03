package ru.otus.spring.homework13.service;

import ru.otus.spring.homework13.domain.Author;

import java.util.List;

public interface AuthorService {

    String insert(Author author);

    List<Author> showAllRows();

    Author showById(long id);

    String deleteById(long id);

}
