package ru.otus.spring.homework06.storage;

import ru.otus.spring.homework06.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author author);

    long count();

    Author getById(long id);

    Author getByName(String authorname);

    List<Author> getAll();

    void delete(long id);
}
