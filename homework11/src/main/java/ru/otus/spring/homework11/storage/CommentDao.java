package ru.otus.spring.homework11.storage;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Comment;

public interface CommentDao extends ReactiveMongoRepository<Comment,String> {

    Mono<Comment> save(Mono<Comment> comment);

    Mono<Comment> getById(String id);

    Flux<Comment> getByBookName(String bookname);

    Flux<Comment> getByBookId(String id);

    //Flux<Comment> getByBookById(String id);

}
