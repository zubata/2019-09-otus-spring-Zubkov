package ru.otus.project.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerForAuth {

    @GetMapping("/")
    public ResponseEntity<String> authenticationSuccess() {
        return ResponseEntity.status(HttpStatus.OK).body("Success Authentication");
    }

}
