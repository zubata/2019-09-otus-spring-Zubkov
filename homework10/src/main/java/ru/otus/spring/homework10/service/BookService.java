package ru.otus.spring.homework10.service;

import ru.otus.spring.homework10.domain.Book;
import ru.otus.spring.homework10.dto.BookDto;

import java.util.List;

public interface BookService {
    Book insert(BookDto bookDto);

    List<Book> showAllRows();

    Book showById(long id);

    void deleteById(long id);

}
