package ru.otus.spring.homework08.service;

public interface BookService {
    String insert();

    void showAllRows();

    void showById();

    void showByName();

    String deleteById();

    String deleteByName();

    void showCount();

    String deleteGenre();

    String updateGenre();
}
