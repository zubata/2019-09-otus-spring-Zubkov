package ru.otus.spring.homework15.service;

import org.springframework.messaging.Message;
import org.springframework.security.access.prepost.PostFilter;
import ru.otus.spring.homework15.domain.Author;
import ru.otus.spring.homework15.domain.Book;
import ru.otus.spring.homework15.domain.Genre;
import ru.otus.spring.homework15.dto.BookDto;

import java.util.List;

public interface BookService {

    Book insert(Book book);

    @PostFilter("hasPermission(filterObject,'READ')")
    List<Book> showAllRows();

    Book showById(long id);

    String deleteById(long id);

    Book convertToDomain(BookDto bookDto);
}
