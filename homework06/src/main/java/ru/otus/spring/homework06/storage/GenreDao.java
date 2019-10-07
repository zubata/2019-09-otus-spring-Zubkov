package ru.otus.spring.homework06.storage;

import ru.otus.spring.homework06.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre genre);

    long count();

    Genre getById(long id);

    Genre getByName(String genrename);

    List<Genre> getAll();

    void delete(long id);
}
