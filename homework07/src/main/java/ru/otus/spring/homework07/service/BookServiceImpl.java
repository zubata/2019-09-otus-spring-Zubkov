package ru.otus.spring.homework07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework07.config.IllegalTextException;
import ru.otus.spring.homework07.domain.Author;
import ru.otus.spring.homework07.domain.Book;
import ru.otus.spring.homework07.domain.Genre;
import ru.otus.spring.homework07.storage.BookDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final IOService ioService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public String insert() {
        ioService.output("Ввести название книги, автора и жанра через точку с запятой");
        String book = ioService.input();
        try {
            String[] mas = checkInputData(book);
            Author tempAuthor = authorService.getAuthor(mas[1]);
            Genre tempGenre = genreService.getGenre(mas[2]);
            Book temp = new Book(mas[0], tempAuthor, tempGenre);
            bookDao.save(temp);
        } catch (IllegalTextException illegalTextException) {
            illegalTextException.printStackTrace();
            return null;
        }
        return book;
    }

    @Override
    public void showAllRows() {
        List<Book> list = bookDao.findAll();
        list.forEach(temp -> ioService.output(temp.toString()));
    }

    @Override
    public void showById() {
        ioService.output("Показать книгу с id");
        long id = Long.parseLong(ioService.input());
        Book temp = bookDao.getById(id);
        ioService.output(temp.toString());
    }

    @Override
    public void showByName() {
        ioService.output("Показать книгу с названием");
        String bookname = ioService.input();
        Book temp = bookDao.getByBookName(bookname);
        ioService.output(temp.toString());
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить книгу с id");
        long id = Long.parseLong(ioService.input());
        bookDao.deleteById(id);
        return String.valueOf(id);
    }

    @Override
    public String deleteByName() {
        ioService.output("Удалить книгу с названием");
        String bookname = ioService.input();
        Book temp = bookDao.getByBookName(bookname);
        long id = temp.getId();
        bookDao.deleteById(id);
        return bookname;
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(bookDao.count()));
    }

    private String[] checkInputData(String book) throws IllegalTextException {
        String[] mas = book.split(" *;");
        if (mas.length != 3) {
            throw new IllegalTextException(book);
        }
        for (int i = 0; i < mas.length; i++) {
            mas[i] = mas[i].trim();
        }
        return mas;
    }

    @Override
    public Book getBook(String bookname) {
        return bookDao.getByBookName(bookname);
    }

}
