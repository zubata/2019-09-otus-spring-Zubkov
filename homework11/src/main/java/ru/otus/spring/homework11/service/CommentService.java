package ru.otus.spring.homework11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Comment;

public interface CommentService {

    Mono<Comment> insert(Comment comment);

    Flux<Comment> getByBook(String bookname);

    Mono<Void> deleteById(String id);

}
