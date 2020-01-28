package ru.otus.spring.homework18.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework18.domain.Book;
import ru.otus.spring.homework18.dto.BookDto;
import ru.otus.spring.homework18.service.AuthorService;
import ru.otus.spring.homework18.service.BookService;

import java.util.List;

@Data
@RestController
public class RestControllerForBooks {
    private final BookService bookService;

    @GetMapping("/api/book")
    public ResponseEntity<List<Book>> getBookAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.showAllRows());
    }

    @PostMapping("/api/book")
    public @ResponseBody
    ResponseEntity<Book> insertBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.insert(bookDto));
    }

    @DeleteMapping("/api/book")
    public ResponseEntity deleteBookById(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.delete(book));
    }
}