package ru.otus.project.feign;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//Надо настроить таймаут
@FeignClient(name = "backend", contextId = "parse")
public interface FeignForParseSite {

    @GetMapping("/api/parse")
    //@Headers("Content-Type: application/json")
    void parseSite();

}
