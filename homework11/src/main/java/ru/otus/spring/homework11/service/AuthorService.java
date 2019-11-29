package ru.otus.spring.homework11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Author;

public interface AuthorService {
    Mono<Author> save(Author author);

    Flux<Author> showAllRows();

    Mono<Author> getById(String id);

    Mono<Author> getByName(String name);

    Mono<Void> deleteById(String id);

}
