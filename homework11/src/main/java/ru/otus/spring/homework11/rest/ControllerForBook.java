package ru.otus.spring.homework11.rest;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.domain.Book;
import ru.otus.spring.homework11.domain.Genre;
import ru.otus.spring.homework11.dto.BookDto;
import ru.otus.spring.homework11.service.AuthorService;
import ru.otus.spring.homework11.service.BookService;
import ru.otus.spring.homework11.service.CommentService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Data
@RestController
public class ControllerForBook {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CommentService commentService;

    @Bean
    public RouterFunction<ServerResponse> controllerBook(BookService bookService) {
        BookHandler bookHandler = new BookHandler(bookService, authorService);
        return route()
                .GET("/api/book", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON).body(bookService.showAllRows(), Book.class))
                .POST("/api/book", contentType(APPLICATION_JSON),
                        bookHandler::createBook
                )
                .DELETE("/api/book", accept(APPLICATION_JSON),
                        request -> request.bodyToMono(Book.class)
                                .flatMap(book -> bookService.deleteById(book.getId())
                                )
                                .then(ok().build())
                )
                .build();
    }

    class BookHandler {

        private final BookService bookService;
        private final AuthorService authorService;


        public BookHandler(BookService bookService, AuthorService authorService) {
            this.bookService = bookService;
            this.authorService = authorService;
        }

        public Mono<ServerResponse> createBook(ServerRequest request) {
            Mono<BookDto> bookDto = request.bodyToMono(BookDto.class);
            Mono<Book> book = bookDto.flatMap(dto -> authorService.getByName(dto.getAuthor())
                    .map(author -> new Book(dto.getName(), author, new Genre(dto.getGenre())))).flatMap(bookService::insert);
            return ok().body(book, Book.class);
        }
    }


}
