package ru.otus.spring.homework07.service;

import ru.otus.spring.homework07.domain.Author;

public interface AuthorService {
    String insert();

    void showAllRows();

    void showById();

    void showByName();

    String deleteById();

    String deleteByName();

    void showCount();

    Author getAuthor(String authorName);
}
