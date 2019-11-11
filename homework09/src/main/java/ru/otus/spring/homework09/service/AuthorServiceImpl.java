package ru.otus.spring.homework09.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework09.domain.Author;
import ru.otus.spring.homework09.exceptions.IllegalAuthorException;
import ru.otus.spring.homework09.storage.AuthorDao;
import ru.otus.spring.homework09.storage.BookDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public String insert(Author author) {
        if(authorDao.save(author)!=null) return String.format("Автор %s успешно добавлен",author.getName());
        else return String.format("Автор %s не был добавлен",author.getName());
    }

    @Override
    public List<Author> showAllRows() { return authorDao.findAll(); }

    @Override
    public Author showById(long id) { return getAuthorById(id); }

    @Override
    public String deleteById(long id) {
        if (authorDao.getById(id) == null) {
            throw new IllegalAuthorException(id);
        }
        authorDao.deleteById(id);
        return String.format("Автор с id %s удален из БД", id);
    }

    private Author getAuthorById(long id) {
        Author temp = authorDao.getById(id);
        if (temp == null) throw new IllegalAuthorException(String.valueOf(id));
        return temp;
    }

}
