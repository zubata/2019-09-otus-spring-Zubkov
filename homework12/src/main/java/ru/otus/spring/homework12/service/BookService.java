package ru.otus.spring.homework12.service;

import ru.otus.spring.homework12.domain.Book;
import ru.otus.spring.homework12.dto.BookDto;

import java.util.List;

public interface BookService {
    String insert(BookDto bookDto);

    List<Book> showAllRows();

    Book showById(long id);

    String deleteById(long id);

}
