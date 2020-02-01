package ru.otus.spring.homework18.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework18.domain.Author;
import ru.otus.spring.homework18.storage.AuthorDao;

import java.util.List;

@RestController
@Data
public class RestControllerForAuthors {

    private final AuthorDao authorDao;

    @GetMapping("/api/author")
    public ResponseEntity<List<Author>> getAuthorAll() {
        return ResponseEntity.status(HttpStatus.OK).body(authorDao.findAll());
    }

    @GetMapping("/api/author/name={name}")
    public ResponseEntity<Author> getAuthorByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(authorDao.getByName(name));
    }

    @PostMapping("/api/author")
    public @ResponseBody
    ResponseEntity<Author> insertBook(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDao.save(author));
    }

    @DeleteMapping("/api/author/id={id}")
    public ResponseEntity deleteAuthorById(@PathVariable long id) {
        authorDao.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
