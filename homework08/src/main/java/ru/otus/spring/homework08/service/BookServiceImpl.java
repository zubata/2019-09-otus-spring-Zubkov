package ru.otus.spring.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Genre;
import ru.otus.spring.homework08.exceptions.IllegalTextException;
import ru.otus.spring.homework08.storage.BookDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final IOService ioService;
    private final CustomService customService;

    @Override
    public String insert() {
        ioService.output("Ввести название книги, автора и жанра через точку с запятой");
        String book = ioService.input();
        try {
            String[] mas = checkInputData(book);
            Author tempAuthor = new Author(mas[1]);
            Genre tempGenre = new Genre(mas[2]);
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
        String id = ioService.input();
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
        String id = ioService.input();
        if (customService.checkAvailableComments(id)) {
            bookDao.deleteById(id);
            id = String.format("Книга с id %s удалена из БД", id);
        } else {
            id = String.format("Книга с id %s не была удалена", id);
        }
        return id;
    }

    @Override
    public String deleteByName() {
        ioService.output("Удалить книгу с названием");
        String bookname = ioService.input();
        Book temp = bookDao.getByName(bookname);
        String id = temp.getId();
        if (customService.checkAvailableComments(id)) {
            bookDao.deleteById(id);
            bookname = String.format("Книга с названием %s удалена из БД", bookname);
        } else {
            bookname = String.format("Книга %s не была удалена", bookname);
        }
        return bookname;
    }

    @Override
    public String deleteGenre() {
        ioService.output("Удалить жанр с названием (жанр будет удалён из всех книг)");
        String genreName = ioService.input();
        List<Book> list = bookDao.getByGenreName(genreName);
        if (list.size() != 0) {
            for (Book temp : list) {
                temp.setGenre(null);
                bookDao.save(temp);
            }
            genreName = String.format("Жанр %s удалён из книг", genreName);
        } else {
            genreName = String.format("Жанр %s не существует в списке книг", genreName);
        }
        return genreName;
    }

    @Override
    public String updateGenre() {
        ioService.output("Для какой книги обновить/удалить жанр");
        String bookname = ioService.input();
        Book temp = customService.checkBook(bookname);
        ioService.output("Какой жанр добавить (для удаления просто нажать enter)");
        String genrename = ioService.input();
        Genre tempGenre = genrename.equals("") ? null : new Genre(genrename);
        temp.setGenre(tempGenre);
        bookDao.save(temp);
        return String.format("Жанр для книги %s обновлён", bookname);
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

}
