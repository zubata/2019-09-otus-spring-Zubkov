package ru.otus.spring.homework06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework06.domain.Author;
import ru.otus.spring.homework06.storage.AuthorDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final IOService ioService;

    @Override
    public String insert() {
        ioService.output("Ввести имя автора");
        String author = ioService.input();
        Author temp = new Author(author.trim());
        authorDao.insert(temp);
        return author;
    }

    @Override
    public void showAllRows() {
        List<Author> list = authorDao.getAll();
        list.forEach(temp -> ioService.output(temp.toString()));
    }

    @Override
    public void showById() {
        ioService.output("Показать автора с id");
        long id = Long.parseLong(ioService.input());
        Author temp = authorDao.getById(id);
        ioService.output(temp.toString());
    }

    @Override
    public void showByName() {
        ioService.output("Показать автора с именем");
        String authorname = ioService.input();
        Author temp = authorDao.getByName(authorname);
        ioService.output(temp.toString());
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить автора с id");
        long id = Long.parseLong(ioService.input());
        authorDao.delete(id);
        return String.valueOf(id);
    }

    @Override
    public String deleteByName() {
        ioService.output("Удалить автора с именем");
        String authorname = ioService.input();
        Author temp = authorDao.getByName(authorname);
        long id = temp.getId();
        authorDao.delete(id);
        return authorname;
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(authorDao.count()));
    }

    @Override
    public Author getAuthor(String authorName) {
        Author temp;
        try {
            temp = authorDao.getByName(authorName);
        } catch (EmptyResultDataAccessException e) {
            authorDao.insert(new Author(authorName));
            temp = authorDao.getByName(authorName);
        }
        return temp;
    }
}
