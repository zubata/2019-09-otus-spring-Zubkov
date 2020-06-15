package ru.otus.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.project.domain.Vine;

import java.util.List;

@FeignClient(name = "backend", contextId = "person")
public interface FeignForPerson {

    @PostMapping("/api/person/vine")
    void addFavouriteVine(long id);

    @DeleteMapping("/api/person/vine")
    void deleteFavouriteVine(long id);

    @DeleteMapping("/api/person/vine/all")
    void deleteAllFavouriteVine();

    @GetMapping("/api/person/vine")
    List<Vine> getFavouriteVines();

}
