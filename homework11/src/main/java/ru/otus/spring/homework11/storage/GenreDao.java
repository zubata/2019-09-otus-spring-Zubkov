package ru.otus.spring.homework11.storage;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Genre;

public interface GenreDao extends ReactiveMongoRepository<Genre,String> {

    Mono<Genre> getById(String id);

    Mono<Genre> save(Mono<Genre> genre);

    Mono<Genre> getByName(String genrename);

}
