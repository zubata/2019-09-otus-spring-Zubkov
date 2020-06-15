package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.project.domain.History;

import java.util.List;

@FeignClient(name = "backend", contextId = "history")
public interface FeignForHistory {

    @GetMapping("/api/vine/history/id")
    List<History> getVineHistoryById(@RequestParam long id);

    @GetMapping("/api/vine/history/name")
    List<History> getVineHistoryByName(@RequestParam String name);

    @DeleteMapping("/api/vine/history/id")
    List<History> deleteVineById(@RequestParam long id);

    @DeleteMapping("/api/vine/history")
    List<History> deleteAllHistory();

}
