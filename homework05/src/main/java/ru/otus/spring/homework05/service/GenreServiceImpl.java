package ru.otus.spring.homework05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework05.domain.Genre;
import ru.otus.spring.homework05.storage.GenreDao;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final IOService ioService;

    @Override
    public String insert() {
        ioService.output("Ввести название жанра и книги через точку с запятой");
        String genre = ioService.input();
        String[] mas = genre.split(";");
        Genre temp = new Genre(mas[0], mas[1]);
        genreDao.insert(temp);
        return genre;
    }

    @Override
    public void showAllRows() {
        List<Genre> list = genreDao.getAll();
        list.stream().map(temp -> String.format("%d %s %s", temp.getId(), temp.getGenreName(), temp.getBookName())).forEach(ioService::output);
    }

    @Override
    public void showById() {
        ioService.output("Показать жанр с id");
        long id = Long.parseLong(ioService.input());
        Genre temp = genreDao.getById(id);
        ioService.output(String.format("%d %s %s", temp.getId(), temp.getGenreName(), temp.getBookName()));
    }

    @Override
    public String delete() {
        ioService.output("Удалить жанр с id");
        long id = Long.parseLong(ioService.input());
        genreDao.delete(id);
        return String.valueOf(id);
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(genreDao.count()));
    }
}
