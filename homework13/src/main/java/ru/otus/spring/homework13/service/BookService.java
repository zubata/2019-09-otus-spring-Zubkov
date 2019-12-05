package ru.otus.spring.homework13.service;

import org.springframework.security.access.prepost.PostFilter;
import ru.otus.spring.homework13.domain.Book;
import ru.otus.spring.homework13.dto.BookDto;

import java.util.List;

public interface BookService {

    String insert(BookDto bookDto);

    @PostFilter("hasPermission(filterObject,'READ')")
    List<Book> showAllRows();

    Book showById(long id);

    String deleteById(long id);

}
