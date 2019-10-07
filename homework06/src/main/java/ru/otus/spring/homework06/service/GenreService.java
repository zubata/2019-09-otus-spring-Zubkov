package ru.otus.spring.homework06.service;

import ru.otus.spring.homework06.domain.Genre;

public interface GenreService {
    String insert();

    void showAllRows();

    void showById();

    void showByName();

    String deleteById();

    String deleteByName();

    void showCount();

    Genre getGenre(String genreName);
}
