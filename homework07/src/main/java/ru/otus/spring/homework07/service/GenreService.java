package ru.otus.spring.homework07.service;

import ru.otus.spring.homework07.domain.Genre;

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
