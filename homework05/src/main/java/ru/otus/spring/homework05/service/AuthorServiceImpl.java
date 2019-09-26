package ru.otus.spring.homework05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework05.domain.Author;
import ru.otus.spring.homework05.storage.AuthorDao;

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
        String[] mas = author.split(";");
        Author temp = new Author(mas[0]);
        authorDao.insert(temp);
        return author;
    }

    @Override
    public void showAllRows() {
        List<Author> list = authorDao.getAll();
        list.stream().map(temp -> String.format("%d %s", temp.getId(), temp.getAuthorName())).forEach(ioService::output);
    }

    @Override
    public void showById() {
        ioService.output("Показать автора с id");
        long id = Long.parseLong(ioService.input());
        Author temp = authorDao.getById(id);
        ioService.output(String.format("%d %s", temp.getId(), temp.getAuthorName()));
    }

    @Override
    public String delete() {
        ioService.output("Удалить автора с id");
        long id = Long.parseLong(ioService.input());
        authorDao.delete(id);
        return String.valueOf(id);
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(authorDao.count()));
    }

    @Override
    public Author getAuthor(String authorName) {
        long authorId;
        try {
            authorId = authorDao.getByName(authorName).getId();
        } catch (EmptyResultDataAccessException e) {
            authorDao.insert(new Author(authorName));
            authorId = authorDao.getByName(authorName).getId();
        }
        return new Author(authorId,authorName);
    }
}
