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
    private final IOService ioService;

    @Override
    public boolean checkAvailableComments(String bookId) {
        int count = commentDao.getByBookId(bookId).size();
        if (count != 0) {
            ioService.output("Имеются комменатрии к книге, которые будут удалены, продолжить? [Y(да)]");
            return ioService.input().toLowerCase().equals("y");
        }
        return false;
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
        if (count != 0) {
            ioService.output("Имеются книги у данного автора, которые будут удалены (в том числе комментарии), продолжить? [Y(да)]");
            return ioService.input().toLowerCase().equals("y");
        }
        return false;
    }
}
