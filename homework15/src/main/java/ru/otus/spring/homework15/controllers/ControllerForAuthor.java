package ru.otus.spring.homework15.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework15.domain.Author;
import ru.otus.spring.homework15.integration.GatewayForAuthor;
import ru.otus.spring.homework15.security.MyAclService;

import java.util.List;

@Data
@Controller
@RequiredArgsConstructor
public class ControllerForAuthor {

    private final MyAclService myAclService;
    private final GatewayForAuthor gateway;

    @GetMapping("/author/id={id}")
    public String getAuthorById(@PathVariable("id") long id, Model model) {
        Author author = gateway.forFindAuthor(id);
        model.addAttribute("author", author);
        return "authorList";
    }

    @GetMapping("/author")
    public String getAuthorAll(Model model) {
        List<Author> authors = gateway.forListAuthor();
        model.addAttribute("author", authors);
        model.addAttribute("adminmode", turnAdminMode());
        return "authorList";
    }

    @PostMapping("/author/add")
    public String insertAuthor(Author author, Model model) {
        Author author1 = gateway.forInsertAuthor(author);
        model.addAttribute("result", author1.getName());
        return "resultInsertAuthor";
    }

    @GetMapping("/author/insertauthor")
    public String getPageInsert(Model model) {
        Author form = new Author();
        model.addAttribute("author", form);
        return "insertAuthor";
    }

    @PostMapping("/author/del")
    public String deleteAuthorById(@RequestParam("id") long id, Model model) {
        String result = gateway.forDeleteAuthor(id);
        model.addAttribute("result", result);
        return "deleteAuthor";
    }

    private boolean turnAdminMode() {
        try {
            return myAclService.isAdmin();
        } catch (AccessDeniedException e) {
            return false;
        }
    }
}
