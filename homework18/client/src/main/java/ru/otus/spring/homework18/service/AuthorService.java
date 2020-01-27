package ru.otus.spring.homework18.service;

import ru.otus.spring.homework18.domain.Author;

import java.util.List;

public interface AuthorService {

    Author insert(Author author);

    List<Author> showAllRows();

    Author showById(long id);

    String delete(Author id);

}
