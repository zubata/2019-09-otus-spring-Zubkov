package ru.otus.spring.homework11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Book;

public interface BookService {
    Mono<Book> insert(Book book);

    Flux<Book> showAllRows();

    Mono<Book> getById(String id);

    Mono<Book> getByName(String name);

    Mono<Void> deleteById(String id);

}
