package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.project.service.BatchService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
public class ControllerForBatch {

    private final BatchService batchService;

    @GetMapping("/vine/save")
    public ResponseEntity<ByteArrayResource> saveVineToCsv() {
        ByteArrayResource resource = batchService.saveToCsv("vineJob");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "Vine.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    @GetMapping("/producer/save")
    public ResponseEntity<ByteArrayResource> saveProducerToCsv() {
        ByteArrayResource resource = batchService.saveToCsv("producerJob");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "Producer.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
