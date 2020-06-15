package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.domain.Vine;

import java.util.List;

@FeignClient(name = "backend", contextId = "vine")
public interface FeignForVine {

    @GetMapping("/api/vine")
    List<Vine> getAllVines();

    @GetMapping("/api/vine/page")
    Page<Vine> getVinesPage(@RequestParam int id);

    @PostMapping("/api/vine/insert")
    Vine insertVine(@RequestBody String vine);

    @GetMapping("/api/vine/name")
    List<Vine> getVineByName(@RequestParam String name);

    @GetMapping("/api/vine/id")
    Vine getVineById(@RequestParam long id);

    @DeleteMapping("/api/vine/del")
    void deleteById(@RequestParam long id);

    @DeleteMapping("/api/vine")
    void deleteAllVine();

}
