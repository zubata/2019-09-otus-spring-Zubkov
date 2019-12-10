package ru.otus.spring.homework14.service;

import ru.otus.spring.homework14.domain.Book;
import ru.otus.spring.homework14.domain.BookDto;

public interface BookService {

    Book convertToDomain(BookDto bookDto);

}
