package ru.otus.spring.homework07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework07.domain.Genre;
import ru.otus.spring.homework07.storage.GenreDao;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final IOService ioService;

    @Override
    public String insert() {
        ioService.output("Ввести название жанра");
        String genre = ioService.input();
        Genre temp = new Genre(genre.trim());
        genreDao.save(temp);
        return genre;
    }

    @Override
    public void showAllRows() {
        List<Genre> list = genreDao.findAll();
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
        Genre temp = genreDao.getBygenreName(genrename);
        ioService.output(temp.toString());
    }

    @Override
    public String deleteById() {
        ioService.output("Удалить жанр с id");
        long id = Long.parseLong(ioService.input());
        genreDao.deleteById(id);
        return String.valueOf(id);
    }

    @Override
    public String deleteByName() {
        ioService.output("Удалить жанр с названием");
        String genrename = ioService.input();
        Genre temp = genreDao.getBygenreName(genrename);
        long id = temp.getId();
        genreDao.deleteById(id);
        return genrename;
    }

    @Override
    public void showCount() {
        ioService.output(String.valueOf(genreDao.count()));
    }

    @Override
    public Genre getGenre(String genreName) {
        Genre temp = genreDao.getBygenreName(genreName);
        if (temp == null) temp = genreDao.save(new Genre(genreName));
        return temp;
    }
}
