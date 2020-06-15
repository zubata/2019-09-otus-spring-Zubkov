package ru.otus.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "backend", contextId = "auth")
public interface FeignForAuth {

    @GetMapping("/api/")
    String personAuth();

    @GetMapping("/api/oauth/token/revoke")
    void revokeToken();

}
