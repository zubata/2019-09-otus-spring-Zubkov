package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.project.domain.Vine;

import java.util.List;

@FeignClient(name = "backend", contextId = "person")
public interface FeignForPerson {

    @PostMapping("/person/vine")
    void addFavouriteVine(long id);

    @DeleteMapping("/person/vine")
    void deleteFavouriteVine(long id);

    @GetMapping("/person/vine")
    List<Vine> getFavouriteVines();

}
