package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.domain.Vine;
import ru.otus.project.service.PersonService;
import ru.otus.project.service.SubscribeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestControllerForPerson {

    private final PersonService personService;
    private final SubscribeService subscribeService;

    @PostMapping("/person/vine")
    public ResponseEntity<Void> addFavouriteVine(@RequestBody long id) {
        personService.addFavouriteVine(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/person/vine")
    public ResponseEntity<Void> deleteFavouriteVine(@RequestBody long id) {
        personService.deleteFavouriteVine(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/person/vine")
    public ResponseEntity<List<Vine>> getFavouriteVines() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getFavouriteVines());
    }
}
