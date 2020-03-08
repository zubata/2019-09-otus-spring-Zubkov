package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.project.domain.History;

import java.util.List;

@FeignClient(name = "backend", contextId = "history")
public interface FeignForHistory {

    @GetMapping("/vine/history/id")
    List<History> getVineHistoryById(@RequestParam long id);

    @GetMapping("/vine/history/name")
    List<History> getVineHistoryByName(@RequestParam String name);

    @DeleteMapping("/vine/history/id")
    List<History> deleteVineById(@RequestParam long id);

    @DeleteMapping("/vine/history")
    List<History> deleteAllHistory();

}
