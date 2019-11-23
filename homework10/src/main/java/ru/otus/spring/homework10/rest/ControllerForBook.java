package ru.otus.spring.homework10.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework10.domain.Book;
import ru.otus.spring.homework10.dto.BookDto;
import ru.otus.spring.homework10.service.AuthorService;
import ru.otus.spring.homework10.service.BookService;

import java.util.List;

@Data
@RestController
public class ControllerForBook {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/api/book")
    public List<Book> getBookAll() { return bookService.showAllRows(); }

    @PostMapping("/api/book")
    public @ResponseBody
    ResponseEntity<Book> insertBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.insert(bookDto));
    }

    @DeleteMapping("/api/book")
    public void deleteBookById(@RequestBody Book book) {
        bookService.deleteById(book.getId());
    }
}
