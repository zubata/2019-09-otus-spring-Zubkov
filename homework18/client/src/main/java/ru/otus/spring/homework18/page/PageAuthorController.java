package ru.otus.spring.homework18.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageAuthorController {

    @GetMapping("/author")
    public String listAuthor() {
        return "authorList";
    }

    @GetMapping("/author/insertauthor")
    public String getPageInsert() { return "insertAuthor"; }

    @GetMapping("/author/result")
    public String getPageResult() { return "resultInsertAuthor"; }

    @GetMapping("/author/del")
    public String getPageDelete() { return "deleteAuthor"; }

}
