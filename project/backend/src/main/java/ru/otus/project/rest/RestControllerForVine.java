package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.domain.Vine;
import ru.otus.project.service.SiteParsingService;
import ru.otus.project.service.SubscribeService;
import ru.otus.project.service.SubscribeServiceImpl;
import ru.otus.project.service.VineService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestControllerForVine {

    private final VineService vineService;

    @PostMapping("/vine/insert")
    public ResponseEntity<Vine> insertVine(@RequestBody String vine) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vineService.insert(vine));
    }

    @GetMapping("/vine")
    public ResponseEntity<List<Vine>> getAllVineList() {
        return ResponseEntity.status(HttpStatus.OK).body(vineService.getList());
    }

    @GetMapping("/vine/name")
    public ResponseEntity<List<Vine>> getVineByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(vineService.getByName(name));
    }

    @GetMapping("/vine/id")
    public @ResponseBody
    ResponseEntity<Vine> getVineById(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(vineService.getById(id));
    }

    @DeleteMapping("/vine/del")
    public ResponseEntity<Void> deleteById(@RequestParam long id) {
        vineService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/vine")
    public ResponseEntity<Void> deleteAllVine() {
        vineService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
