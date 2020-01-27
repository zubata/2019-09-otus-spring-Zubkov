package ru.otus.spring.homework18.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework18.domain.Book;
import ru.otus.spring.homework18.storage.BookDao;

import java.util.List;

@Data
@RestController
public class RestForBooks {
    private final BookDao bookService;

    @GetMapping("/api/book")
    public ResponseEntity<List<Book>> getBookAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("/api/book/name={name}")
    public ResponseEntity<Book> getBookByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getByName(name));
    }

    @PostMapping("/api/book")
    public @ResponseBody
    ResponseEntity<Book> insertBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }

    @DeleteMapping("/api/book")
    public ResponseEntity deleteBookById(@RequestBody Book book) {
        bookService.deleteById(book.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}