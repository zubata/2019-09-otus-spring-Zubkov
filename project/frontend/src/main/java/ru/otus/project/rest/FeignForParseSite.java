package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//Надо настроить таймаут
@FeignClient(name = "backend", contextId = "parse")
public interface FeignForParseSite {

    @GetMapping("/parse")
    void parseSite();

}
