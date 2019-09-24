package ru.otus.spring.homework05.storage;

import ru.otus.spring.homework05.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author author);

    int count();

    Author getById(long id);

    List getAll();

    void delete(long id);
}
