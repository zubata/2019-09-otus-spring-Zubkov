package ru.otus.spring.homework11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Book;
import ru.otus.spring.homework11.exceptions.IllegalBookException;
import ru.otus.spring.homework11.storage.BookDao;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public Mono<Book> insert(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Flux<Book> showAllRows() {
        return bookDao.findAll();
    }

    @Override
    public Mono<Book> getById(String id) {
        Mono<Book> temp = bookDao.getById(id);
        if (temp == null) throw new IllegalBookException(id);
        return temp;
    }

    @Override
    public Mono<Book> getByName(String name) {
        Mono<Book> temp = bookDao.getByName(name);
        if (temp == null) throw new IllegalBookException(name);
        return temp;
    }

    @Override
    public Mono<Void> deleteById(String  id) {
        if (bookDao.getById(id) == null) {
            throw new IllegalBookException(id);
        }
        return bookDao.deleteById(id);
    }

}
