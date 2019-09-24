package ru.otus.spring.homework05.storage;

import ru.otus.spring.homework05.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre genre);

    int count();

    Genre getById(long id);

    List getAll();

    void delete(long id);
}
