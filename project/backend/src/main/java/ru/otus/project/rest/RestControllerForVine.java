package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.domain.Vine;
import ru.otus.project.service.VineService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestControllerForVine {

    private final VineService vineService;

    @PostMapping("/vine/insert")
    @ResponseBody
    public ResponseEntity<Vine> insertVine(@RequestBody String vine) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vineService.insert(vine));
    }

    @GetMapping("/vine")
    @ResponseBody
    public List<Vine> getAllVineList() {
        return vineService.getList();
    }

    @GetMapping("/vine/page")
    @ResponseBody
    public Page<Vine> getVineListPage(@RequestParam(defaultValue = "0") int id) {
        return vineService.getListPage(id);
    }

    @GetMapping("/vine/name")
    @ResponseBody
    public List<Vine> getVineByName(@RequestParam String name) {
        return vineService.getByName(name);
    }

    @GetMapping("/vine/id")
    @ResponseBody
    public Vine getVineById(@RequestParam long id) {
        return vineService.getById(id);
    }

    @GetMapping("/vine/producer/id")
    @ResponseBody
    public List<Vine> getVineByProducer(@RequestParam long id) {
        return vineService.getByProducerId(id);
    }

    @DeleteMapping("/vine/del")
    @ResponseBody
    public void deleteById(@RequestParam long id) {
        vineService.deleteById(id);
    }

    @DeleteMapping("/vine")
    @ResponseBody
    public void deleteAllVine() {
        vineService.deleteAll();
    }

}
