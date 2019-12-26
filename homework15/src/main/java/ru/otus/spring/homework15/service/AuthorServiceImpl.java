package ru.otus.spring.homework15.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework15.domain.Author;
import ru.otus.spring.homework15.exceptions.IllegalAuthorException;
import ru.otus.spring.homework15.storage.AuthorDao;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Author insert(Author author) {
        return authorDao.save(author);
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
