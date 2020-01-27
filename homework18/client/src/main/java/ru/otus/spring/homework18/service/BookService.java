package ru.otus.spring.homework18.service;

import org.springframework.security.access.prepost.PostFilter;
import ru.otus.spring.homework18.domain.Book;
import ru.otus.spring.homework18.dto.BookDto;

import java.util.List;

public interface BookService {

    Book insert(BookDto bookDto);

    @PostFilter("hasPermission(filterObject,'READ')")
    List<Book> showAllRows();

    Book showById(long id);

    String delete(Book book);

}
