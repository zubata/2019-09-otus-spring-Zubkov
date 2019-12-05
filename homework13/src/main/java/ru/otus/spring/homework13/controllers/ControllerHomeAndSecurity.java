package ru.otus.spring.homework13.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return "authenticated";
    }

    @GetMapping("/errorlogin")
    public String getErrorPage() { return "errorLogin"; }

}
