package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.domain.Producer;
import ru.otus.project.service.ProducerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestControllerForProduct {

    private final ProducerService producerService;

    @GetMapping("/producer")
    public ResponseEntity<List<Producer>> getAllProducerList() {
        return ResponseEntity.status(HttpStatus.OK).body(producerService.getList());
    }

    @GetMapping("/producer/name")
    public ResponseEntity<Producer> getProducerByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(producerService.getByName(name));
    }

    @GetMapping("/producer/id")
    public ResponseEntity<Producer> getProducerById(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(producerService.getById(id));
    }

    @DeleteMapping("/producer/id")
    public ResponseEntity deleteById(@RequestParam long id) {
        producerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/producer")
    public ResponseEntity deleteAllProducer() {
        producerService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
