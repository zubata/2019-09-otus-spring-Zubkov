package ru.otus.spring.homework11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Author;
import ru.otus.spring.homework11.exceptions.IllegalAuthorException;
import ru.otus.spring.homework11.storage.AuthorDao;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Mono<Author> save(Author author) { return authorDao.save(author); }

    @Override
    public Flux<Author> showAllRows() {
        return authorDao.findAll();
    }

    @Override
    public Mono<Author> getById(String id) {
        Mono<Author> temp = authorDao.getById(id);
        if (temp == null) throw new IllegalAuthorException(String.valueOf(id));
        return temp;
    }

    @Override
    public Mono<Author> getByName(String name) {
        Mono<Author> temp = authorDao.getByName(name);
        if (temp == null) throw new IllegalAuthorException(name);
        return temp;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        if (authorDao.getById(id) == null) {
            throw new IllegalAuthorException(id);
        }
        return authorDao.deleteById(id);
    }

}
