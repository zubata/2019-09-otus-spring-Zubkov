package ru.otus.project.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class RestControllerForAuth {

    @GetMapping("/")
    public String authenticationSuccess() {
        return "Success Authentication";
    }

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }

}
