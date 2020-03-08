package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "backend", contextId = "batch")
public interface FeignForBatch {

    @PostMapping("/vine/save")
    void saveVinesToCsv(@RequestBody String path);

    @GetMapping("/vine/restart")
    void restartVinesToCsv();

    @PostMapping("/producer/save")
    void saveProducerToCsv(@RequestBody String path);

    @GetMapping("/producer/restart")
    void restartProducerToCsv();

}
