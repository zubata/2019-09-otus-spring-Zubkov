package ru.otus.spring.homework11.storage;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Book;

public interface BookDao extends ReactiveMongoRepository<Book, String> {

    Mono<Book> save(Mono<Book> book);

    Mono<Book> getById(String id);

    Mono<Book> getByName(String bookname);

    Flux<Book> getByAuthorId(String authorId);

    Flux<Book> findAll();

}
