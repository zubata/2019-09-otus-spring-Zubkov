package ru.otus.spring.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Genre;
import ru.otus.spring.homework08.exceptions.IllegalBookException;
import ru.otus.spring.homework08.storage.BookDao;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private final BookDao bookDao;
    private final IOService ioService;

    @Override
    public String deleteGenre() {
        ioService.output("Удалить жанр с названием (жанр будет удалён из всех книг)");
        String genreName = ioService.input();
        List<Book> list = bookDao.getByGenreListName(genreName);
        if (list.size() > 1) {
            for (Book temp : list) {
                temp.deleteGenre(new Genre(genreName));
                bookDao.save(temp);
            }
            return String.format("Жанр %s удалён из книг", genreName);
        } else if (list.size() == 1) {
            return String.format("Жанр %s единственный в книге, соответственно надо удалить целиком книгу", genreName);
        } else {
            return String.format("Жанр %s не существует в списке книг", genreName);
        }
    }

    @Override
    public String updateGenre() {
        ioService.output("Для какой книги обновить/удалить жанр");
        String bookname = ioService.input();
        Book temp = bookDao.getByName(bookname);
        if (temp == null) {
            throw new IllegalBookException(bookname);
        }
        ioService.output("Какой жанр добавить (для удаления просто нажать enter)");
        String genrename = ioService.input();
        Genre tempGenre = genrename.equals("") ? null : new Genre(genrename);
        temp.addGenre(tempGenre);
        bookDao.save(temp);
        return String.format("Жанр для книги %s обновлён", bookname);
    }

}
