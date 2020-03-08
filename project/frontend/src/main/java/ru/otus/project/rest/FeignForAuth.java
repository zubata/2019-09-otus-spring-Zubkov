package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "backend", contextId = "auth")
public interface FeignForAuth {

    @GetMapping("/")
    String personAuth();
}
