package ru.otus.spring.homework06.storage;

import ru.otus.spring.homework06.domain.Book;

import java.util.List;

public interface BookDao {
    void insert(Book Book);

    long count();

    Book getById(long id);

    Book getByName(String bookname);

    List<Book> getAll();

    void delete(long id);
}
