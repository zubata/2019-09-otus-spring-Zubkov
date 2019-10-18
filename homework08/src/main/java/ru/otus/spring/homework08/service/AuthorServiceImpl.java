package ru.otus.spring.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.storage.AuthorDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final IOService ioService;

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
        Author temp = authorDao.getById(id);
        ioService.output(temp.toString());
    }

    @Override
    public void showByName() {
        ioService.output("Показать автора с именем");
        String authorname = ioService.input();
        Author temp = authorDao.getByAuthorName(authorname);
        ioService.output(temp.toString());
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить автора с id");
        String id = ioService.input();
        authorDao.deleteById(id);
        return String.valueOf(id);
    }

    @Override
    public String deleteByName() {
        ioService.output("Удалить автора с именем");
        String authorname = ioService.input();
        Author temp = authorDao.getByAuthorName(authorname);
        String id = temp.getId();
        authorDao.deleteById(id);
        return authorname;
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(authorDao.count()));
    }

}
