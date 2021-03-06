package ru.otus.spring.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.exceptions.IllegalAuthorException;
import ru.otus.spring.homework08.storage.AuthorDao;
import ru.otus.spring.homework08.storage.BookDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final IOService ioService;
    private final BookDao bookDao;

    @Override
    public String insert() {
        ioService.output("Ввести имя автора");
        String author = ioService.input();
        Author temp = new Author(author.trim());
        authorDao.save(temp);
        return author;
    }

    @Override
    public void showAllRows() {
        List<Author> list = authorDao.findAll();
        list.forEach(temp -> ioService.output(temp.toString()));
    }

    @Override
    public void showById() {
        ioService.output("Показать автора с id");
        String id = ioService.input();
        Author temp = getAuthorById(id);
        ioService.output(temp.toString());
    }

    @Override
    public void showByName() {
        ioService.output("Показать автора с именем");
        String authorname = ioService.input();
        Author temp = getAuthorByName(authorname);
        ioService.output(temp.toString());
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить автора с id");
        String id = ioService.input();
        if (authorDao.getById(id) == null) {
            throw new IllegalAuthorException(id);
        }
        if (deleteOrNot(id)) {
            authorDao.deleteById(id);
            return String.format("Автор с id %s удален из БД", id);
        } else {
            return String.format("Автор с id %s не был удален из БД", id);
        }
    }

    @Override
    public String deleteByName() {
        ioService.output("Удалить автора с именем");
        String authorname = ioService.input();
        Author temp = getAuthorByName(authorname);
        String id = temp.getId();
        if (deleteOrNot(id)) {
            authorDao.deleteById(id);
            return String.format("Автор с именем %s удален из БД", authorname);
        } else {
            return String.format("Автор с именем %s не был удален из БД", authorname);
        }
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(authorDao.count()));
    }

    private boolean deleteOrNot(String id) {
        if (checkAvailableBooks(id)) {
            ioService.output("Имеются книги у данного автора, которые будут удалены (в том числе комментарии), продолжить? [Y(да)]");
            return ioService.input().toLowerCase().equals("y");
        }
        return true;
    }

    private Author getAuthorById(String id) {
        Author temp = authorDao.getById(id);
        if (temp == null) throw new IllegalAuthorException(id);
        return temp;
    }

    private Author getAuthorByName(String name) {
        Author temp = authorDao.getByAuthorName(name);
        if (temp == null) throw new IllegalAuthorException(name);
        return temp;
    }

    public boolean checkAvailableBooks(String authorId) {
        long count = bookDao.countByAuthorId(authorId);
        return count != 0;
    }

}
