package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.project.domain.Producer;

import java.util.List;

@FeignClient(name = "backend", contextId = "producer")
public interface FeignForProducer {

    @GetMapping("/api/producer")
    List<Producer> getProducerList();

    @GetMapping("/api/producer/name")
    Producer getProducerByName(@RequestParam String name);

    @GetMapping("/api/producer/id")
    Producer getProducerById(@RequestParam long id);

    @DeleteMapping("/api/producer/id")
    void deleteById(@RequestParam long id);

    @DeleteMapping("/api/producer")
    void deleteAllProducer();

}
