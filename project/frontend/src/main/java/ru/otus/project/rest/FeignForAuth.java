package ru.otus.project.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "backend", contextId = "auth")
public interface FeignForAuth {

    @PostMapping(value = "/api/oauth/token", consumes = "application/x-www-form-urlencoded")
    String personAuth(@RequestBody String body);

    @GetMapping("/api/oauth/token/revoke")
    void revokeToken();
}
