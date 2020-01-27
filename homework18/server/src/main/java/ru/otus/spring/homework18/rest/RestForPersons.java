package ru.otus.spring.homework18.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework18.domain.Person;
import ru.otus.spring.homework18.storage.PersonDao;

@Data
@RestController
public class RestForPersons {
    private final PersonDao personDao;

    @GetMapping("/api/person/name={name}")
    public ResponseEntity<Person> getAuthorByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(personDao.findByUsername(name).get());
    }
}
