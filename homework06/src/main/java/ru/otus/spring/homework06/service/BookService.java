package ru.otus.spring.homework06.service;

import ru.otus.spring.homework06.domain.Book;

public interface BookService {
    String insert();

    void showAllRows();

    void showById();

    void showByName();

    String deleteById();

    String deleteByName();

    void showCount();

    Book getBook(String bookname);
}
