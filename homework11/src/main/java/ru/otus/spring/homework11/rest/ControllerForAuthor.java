package ru.otus.spring.homework11.rest;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.homework11.domain.Author;
import ru.otus.spring.homework11.service.AuthorService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Data
@Configuration
public class ControllerForAuthor {

    private final AuthorService authorService;

    @Bean
    public RouterFunction<ServerResponse> controllerAuthor(AuthorService authorService) {
        return route()
                .GET("/api/author", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON).body(authorService.showAllRows(), Author.class))
                .POST("/api/author", accept(APPLICATION_JSON)
                        , request -> request.bodyToMono(Author.class)
                                .map(authorService::save).flatMap(aut -> ok().body(aut, Author.class))
                )
                .DELETE("/api/author", accept(APPLICATION_JSON),
                        request -> request.bodyToMono(Author.class)
                        .map(author->authorService.deleteById(author.getId()).subscribe())
                                .then(ok().build())
                )
                .build();
    }

}
