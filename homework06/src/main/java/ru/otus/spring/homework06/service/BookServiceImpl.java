package ru.otus.spring.homework06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework06.config.IllegalText;
import ru.otus.spring.homework06.domain.Author;
import ru.otus.spring.homework06.domain.Book;
import ru.otus.spring.homework06.domain.Genre;
import ru.otus.spring.homework06.storage.BookDao;

import java.util.List;

@Service
@RequiredArgsConstructor
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
            bookDao.insert(temp);
        } catch (IllegalText illegalText) {
            illegalText.printStackTrace();
            return null;
        }
        return book;
    }

    @Override
    public void showAllRows() {
        List<Book> list = bookDao.getAll();
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
        Book temp = bookDao.getByName(bookname);
        ioService.output(temp.toString());
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить книгу с id");
        long id = Long.parseLong(ioService.input());
        bookDao.delete(id);
        return String.valueOf(id);
    }

    @Override
    public String deleteByName() {
        ioService.output("Удалить книгу с названием");
        String bookname = ioService.input();
        Book temp = bookDao.getByName(bookname);
        long id = temp.getId();
        bookDao.delete(id);
        return bookname;
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(bookDao.count()));
    }

    private String[] checkInputData(String book) throws IllegalText {
        String[] mas = book.split(" *;");
        if (mas.length != 3) {
            throw new IllegalText(book);
        }
        for (int i = 0; i < mas.length; i++) {
            mas[i] = mas[i].trim();
        }
        return mas;
    }

    @Override
    public Book getBook(String bookname) {
        return bookDao.getByName(bookname);
    }

}
