package ru.otus.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "backend", contextId = "batch")
public interface FeignForBatch {

    @GetMapping("/api/vine/save")
    ByteArrayResource saveVinesToCsv();

    @GetMapping("/api/producer/save")
    ByteArrayResource saveProducerToCsv();

}
