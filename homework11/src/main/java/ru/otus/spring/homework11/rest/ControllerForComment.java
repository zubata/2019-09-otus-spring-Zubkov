package ru.otus.spring.homework11.rest;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.scheduler.Schedulers;
import ru.otus.spring.homework11.domain.Comment;
import ru.otus.spring.homework11.dto.CommentDto;
import ru.otus.spring.homework11.service.BookService;
import ru.otus.spring.homework11.service.CommentService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Data
@RestController
public class ControllerForComment {
    private final CommentService commentService;
    private final BookService bookService;

    @Bean
    public RouterFunction<ServerResponse> controllerComment(CommentService commentService) {
        return route()
                .GET("/api/comment/book", accept(APPLICATION_JSON),
                        request -> request.queryParam("bookname")
                                .map(commentService::getByBook)
                                .map(comments -> ok().body(comments, Comment.class)).orElse(notFound().build()))
                .POST("/api/comment/book", accept(APPLICATION_JSON)
                        , request -> request.bodyToMono(CommentDto.class)
                                .flatMap(dto -> bookService.getByName(dto.getBookname())
                                        .map(Comment::new)
                                        .doOnNext(comment -> comment.setComment(dto.getComment())))
                                .subscribeOn(Schedulers.elastic())
                                .map(commentService::insert)
                                .flatMap(comment -> ok().body(comment, Comment.class))
                )
                .DELETE("/api/comment/book", accept(APPLICATION_JSON),
                        request -> request.bodyToMono(Comment.class)
                                .map(comment -> commentService.deleteById(comment.getId()).subscribe())
                                .then(ok().build())
                )
                .build();
    }
}
