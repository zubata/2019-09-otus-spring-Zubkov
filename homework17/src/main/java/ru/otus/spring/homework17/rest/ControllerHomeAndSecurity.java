package ru.otus.spring.homework17.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerHomeAndSecurity {

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/errorlogin")
    public String getErrorPage() { return "errorLogin"; }

}
