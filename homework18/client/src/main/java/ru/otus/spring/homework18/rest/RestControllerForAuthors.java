package ru.otus.spring.homework18.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework18.domain.Author;
import ru.otus.spring.homework18.service.AuthorService;

import java.util.List;

@RestController
@Data
public class RestControllerForAuthors {

    private final AuthorService authorService;

    @GetMapping("/api/author")
    public ResponseEntity<List<Author>> getAuthorAll() {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.showAllRows());
    }

    @PostMapping("/api/author")
    public @ResponseBody
    ResponseEntity<Author> insertBook(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.insert(author));
    }

    @DeleteMapping("/api/author")
    public ResponseEntity deleteAuthorById(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.delete(author));
    }
}
