package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.service.BatchService;

@RestController
@RequiredArgsConstructor
public class RestControllerForBatch {

    private final BatchService batchService;

    @PostMapping("/vine/save")
    public ResponseEntity<Void> saveVinesToFile(@RequestBody String path) {
        batchService.saveToCsv("vineJob", path);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/vine/restart")
    public ResponseEntity<Void> restartSaveVinesToFile() {
        batchService.restartJob("vineJob");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/producer/save")
    public ResponseEntity<Void> saveProducersToFile(@RequestBody String path) {
        batchService.saveToCsv("producerJob", path);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/producer/restart")
    public ResponseEntity<Void> restartSaveProducersToFile() {
        batchService.restartJob("producerJob");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
