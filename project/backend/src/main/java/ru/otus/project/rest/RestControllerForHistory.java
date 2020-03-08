package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.domain.History;
import ru.otus.project.service.HistoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestControllerForHistory {

    private final HistoryService historyService;

    @GetMapping("/vine/history/id")
    public ResponseEntity<List<History>> showVineHistoryById(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(historyService.getHistoryByVineId(id));
    }

    @GetMapping("/vine/history/name")
    public ResponseEntity<List<History>> showVineHistoryByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(historyService.getHistoryByVineName(name));
    }

    @DeleteMapping("/vine/history/id")
    public ResponseEntity<Void> deleteVineHistory(@RequestParam long id) {
        historyService.deleteByVineId(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/vine/history")
    public ResponseEntity<Void> deleteAllHistory(@RequestParam long id) {
        historyService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
