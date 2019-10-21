package ru.otus.spring.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.exceptions.IllegalBookException;
import ru.otus.spring.homework08.storage.BookDao;
import ru.otus.spring.homework08.storage.CommentDao;

@Component
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

    private final BookDao bookDao;
    private final CommentDao commentDao;

    @Override
    public boolean checkAvailableComments(String bookId) {
        int count = commentDao.getByBookId(bookId).size();
        return count != 0;
    }

    @Override
    public Book checkBook(String bookname) throws IllegalBookException {
        Book temp = bookDao.getByName(bookname);
        if (temp == null) throw new IllegalBookException(bookname);
        return temp;
    }

    @Override
    public boolean checkAvailableBooks(String authorId) {
        int count = bookDao.getByAuthorId(authorId).size();
        return count != 0;
    }
}
