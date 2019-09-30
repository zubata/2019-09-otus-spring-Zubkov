package ru.otus.spring.homework05.service;

import ru.otus.spring.homework05.domain.Genre;

public interface GenreService {
    String insert();

    void showAllRows();

    void showById();

    String delete();

    void showCount();

    Genre getGenre(String genreName);
}
