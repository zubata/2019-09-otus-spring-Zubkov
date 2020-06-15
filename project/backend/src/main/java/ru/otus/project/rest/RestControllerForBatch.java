package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.service.BatchService;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class RestControllerForBatch {

    private final BatchService batchService;

    @GetMapping("/vine/save")
    public ResponseEntity<ByteArrayResource> saveVinesToFile() throws IOException {
        File file = batchService.saveToCsv("vineJob");
        byte[] data = Files.readAllBytes(Paths.get(file.getPath()));
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    @GetMapping("/producer/save")
    public ResponseEntity<ByteArrayResource> saveProducersToFile() throws IOException {
        File file = batchService.saveToCsv("producerJob");
        byte[] data = Files.readAllBytes(Paths.get(file.getPath()));
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }


    @GetMapping("/vine/restart")
    public ResponseEntity<Void> restartSaveVinesToFile() {
        batchService.restartJob("vineJob");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/producer/restart")
    public ResponseEntity<Void> restartSaveProducersToFile() {
        batchService.restartJob("producerJob");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
