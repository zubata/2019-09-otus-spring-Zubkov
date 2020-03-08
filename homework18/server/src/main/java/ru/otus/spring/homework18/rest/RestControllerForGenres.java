package ru.otus.spring.homework18.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework18.domain.Author;
import ru.otus.spring.homework18.domain.Genre;
import ru.otus.spring.homework18.storage.GenreDao;

@Data
@RestController
public class RestControllerForGenres {
    private final GenreDao genreDao;

    @GetMapping("/api/genre/name={name}")
    public ResponseEntity<Genre> getAuthorByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(genreDao.getByName(name));
    }

    @PostMapping("/api/genre")
    public @ResponseBody
    ResponseEntity<Genre> insertBook(@RequestBody Genre genre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreDao.save(genre));
    }

}
