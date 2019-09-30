package ru.otus.spring.homework05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework05.config.IllegalText;
import ru.otus.spring.homework05.domain.Author;
import ru.otus.spring.homework05.domain.Book;
import ru.otus.spring.homework05.domain.Genre;
import ru.otus.spring.homework05.storage.BookDao;

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
        String[] mas = new String[0];
        try {
            mas = checkInputData(book);
        } catch (IllegalText illegalText) {
            illegalText.printStackTrace();
        }
        Author tempAuthor = authorService.getAuthor(mas[1]);
        Genre tempGenre = genreService.getGenre(mas[2]);
        Book temp = new Book(mas[0], tempAuthor, tempGenre);
        bookDao.insert(temp);
        return book;
    }

    @Override
    public void showAllRows() {
        List<Book> list = bookDao.getAll();
        list.stream().map(temp -> String.format("%d %s %s %s", temp.getId(), temp.getBookName(),
                temp.getAuthor().getAuthorName(),
                temp.getGenre().getGenreName())).forEach(ioService::output);
    }

    @Override
    public void showById() {
        ioService.output("Показать книгу с id");
        long id = Long.parseLong(ioService.input());
        Book temp = bookDao.getById(id);
        ioService.output(String.format("%d %s %s %s", temp.getId(), temp.getBookName(),
                temp.getAuthor().getAuthorName(),
                temp.getGenre().getGenreName()));
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

    @Override
    public String[] checkInputData(String book) throws IllegalText {
        String[] mas = book.split(" *;");
        if (mas.length != 3) {
            throw new IllegalText(book);
        }
        return mas;
    }
}
