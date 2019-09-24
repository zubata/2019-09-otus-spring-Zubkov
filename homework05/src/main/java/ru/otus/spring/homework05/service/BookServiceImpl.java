package ru.otus.spring.homework05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework05.domain.Book;
import ru.otus.spring.homework05.storage.BookDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final IOService ioService;

    @Override
    public String insert() {
        ioService.output("Ввести название книги и автора через точку с запятой");
        String book = ioService.input();
        String[] mas = book.split(";");
        Book temp = new Book(mas[0],mas[1]);
        bookDao.insert(temp);
        return book;
    }

    @Override
    public void showAllRows() {
        List<Book> list = bookDao.getAll();
        list.stream().map(temp -> String.format("%d %s %s", temp.getId(), temp.getBookName(), temp.getAuthorName())).forEach(ioService::output);
    }

    @Override
    public void showById() {
        ioService.output("Показать книгу с id");
        long id = Long.parseLong(ioService.input());
        Book temp = bookDao.getById(id);
        ioService.output(String.format("%d %s %s", temp.getId(), temp.getBookName(), temp.getAuthorName()));
    }

    @Override
    public String delete() {
        ioService.output("Удалить книгу с id");
        long id = Long.parseLong(ioService.input());
        bookDao.delete(id);
        return String.valueOf(id);
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(bookDao.count()));
    }
}
