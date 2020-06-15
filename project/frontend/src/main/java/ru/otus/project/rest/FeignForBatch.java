package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "backend", contextId = "batch")
public interface FeignForBatch {

    @PostMapping("/api/vine/save")
    void saveVinesToCsv(@RequestBody String path);

    @GetMapping("/api/vine/restart")
    void restartVinesToCsv();

    @PostMapping("/api/producer/save")
    void saveProducerToCsv(@RequestBody String path);

    @GetMapping("/api/producer/restart")
    void restartProducerToCsv();

}
