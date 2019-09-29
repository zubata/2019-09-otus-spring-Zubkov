package ru.otus.spring.homework05.storage;

import ru.otus.spring.homework05.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author author);

    int count();

    Author getById(long id);

    Author getByName(String authorname);

    List<Author> getAll();

    void delete(long id);
}
