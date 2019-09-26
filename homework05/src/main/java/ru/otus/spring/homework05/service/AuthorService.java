package ru.otus.spring.homework05.service;

import ru.otus.spring.homework05.domain.Author;

public interface AuthorService {
    String insert();

    void showAllRows();

    void showById();

    String delete();

    void showCount();

    Author getAuthor(String authorName);
}
