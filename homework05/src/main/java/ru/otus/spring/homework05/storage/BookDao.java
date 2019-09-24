package ru.otus.spring.homework05.storage;

import ru.otus.spring.homework05.domain.Book;

import java.util.List;

public interface BookDao {
    void insert(Book Book);

    int count();

    Book getById(long id);

    List getAll();

    void delete(long id);
}
