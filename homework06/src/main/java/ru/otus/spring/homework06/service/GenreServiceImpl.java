package ru.otus.spring.homework06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework06.domain.Genre;
import ru.otus.spring.homework06.storage.GenreDao;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final IOService ioService;

    @Override
    public String insert() {
        ioService.output("Ввести название жанра");
        String genre = ioService.input();
        Genre temp = new Genre(genre.trim());
        genreDao.insert(temp);
        return genre;
    }

    @Override
    public void showAllRows() {
        List<Genre> list = genreDao.getAll();
        list.forEach(temp -> ioService.output(temp.toString()));
    }

    @Override
    public void showById() {
        ioService.output("Показать жанр с id");
        long id = Long.parseLong(ioService.input());
        Genre temp = genreDao.getById(id);
        ioService.output(temp.toString());
    }

    @Override
    public void showByName() {
        ioService.output("Показать жанр с названием");
        String genrename = ioService.input();
        Genre temp = genreDao.getByName(genrename);
        ioService.output(temp.toString());
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить жанр с id");
        long id = Long.parseLong(ioService.input());
        genreDao.delete(id);
        return String.valueOf(id);
    }

    @Override
    public String deleteByName() {
        ioService.output("Удалить жанр с названием");
        String genrename = ioService.input();
        Genre temp = genreDao.getByName(genrename);
        long id = temp.getId();
        genreDao.delete(id);
        return genrename;
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(genreDao.count()));
    }

    @Override
    public Genre getGenre(String genreName) {
        long genreId;
        try {
            genreId = genreDao.getByName(genreName).getId();
        } catch (EmptyResultDataAccessException e) {
            genreDao.insert(new Genre(genreName));
            genreId = genreDao.getByName(genreName).getId();
        }
        return new Genre(genreId, genreName);
    }
}
