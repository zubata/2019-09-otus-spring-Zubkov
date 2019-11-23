package ru.otus.spring.homework10.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework10.domain.Author;
import ru.otus.spring.homework10.service.AuthorService;

import java.util.List;

@Data
@RestController
public class ControllerForAuthor {

    private final AuthorService authorService;

    @GetMapping("/api/author")
    public List<Author> getAuthorAll() {
        return authorService.showAllRows();
    }

    @PostMapping("/api/author")
    public @ResponseBody
    ResponseEntity<Author> insertAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.insert(author));
    }


    @DeleteMapping("/api/author")
    public void deleteAuthorById(@RequestBody Author author) {
        authorService.deleteById(author.getId());
    }
}
