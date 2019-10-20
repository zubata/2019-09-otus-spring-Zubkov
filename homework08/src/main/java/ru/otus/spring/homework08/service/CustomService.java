package ru.otus.spring.homework08.service;

import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.exceptions.IllegalBookException;

public interface CustomService {

    boolean checkAvailableComments(String bookId);

    Book checkBook(String bookname) throws IllegalBookException;

    boolean checkAvailableBooks(String authorId);

}
