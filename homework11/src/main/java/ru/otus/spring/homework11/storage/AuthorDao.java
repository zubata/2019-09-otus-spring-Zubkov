package ru.otus.spring.homework11.storage;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Author;

public interface AuthorDao extends ReactiveMongoRepository<Author, String> {

    Mono<Author> getById(String id);

    Mono<Author> getByName(String authorname);

}
