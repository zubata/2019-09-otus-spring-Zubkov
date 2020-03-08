package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.domain.Vine;

import java.util.List;

@FeignClient(name = "backend", contextId = "vine")
public interface FeignForVine {

    @GetMapping("/vine")
    List<Vine> getAllVines();

    @PostMapping("/vine/insert")
    Vine insertVine(@RequestBody String vine);

    @GetMapping("/vine/name")
    List<Vine> getVineByName(@RequestParam String name);

    @GetMapping("/vine/id")
    Vine getVineById(@RequestParam long id);

    @DeleteMapping("/vine/del")
    void deleteById(@RequestParam long id);

    @DeleteMapping("/vine")
    void deleteAllVine();

}
