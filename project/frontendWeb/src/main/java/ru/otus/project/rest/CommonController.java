package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.project.service.ParseSiteService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommonController {

    private final ParseSiteService parseSiteService;

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        model.addAttribute("principal", principal);
        return principal != null ? "indexWithAuth" : "index";
    }

    @GetMapping("/parse")
    public String parse(Model model) {
        long t1 = System.currentTimeMillis() / 1000;
        parseSiteService.parseSite();
        long t2 = System.currentTimeMillis() / 1000;
        model.addAttribute("time", t2 - t1);
        return "parse";
    }


}
