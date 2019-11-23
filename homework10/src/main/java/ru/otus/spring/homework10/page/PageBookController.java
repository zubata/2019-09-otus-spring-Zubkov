package ru.otus.spring.homework10.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageBookController {

    @GetMapping("/")
    public String listBook() { return "bookList"; }

    @GetMapping("/book/insertbook")
    public String getPageInsert() { return "insertBook"; }

    @GetMapping("/book/result")
    public String getPageInsertResult() { return "resultInsertBook";}

    @GetMapping("/book/del")
    public String getPageDelete() { return "deleteBook"; }

}
