package ru.otus.spring.homework18.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageCommentConroller {

    @GetMapping("/comment/book")
    public String listComments(@RequestParam("bookname") String bookname) { return "commentList"; }

    @GetMapping("/comment/insertcomment")
    public String getPageInsert(@RequestParam("bookname") String bookname) { return "insertComment"; }

    @GetMapping("/comment/result")
    public String getPageInsertResult(@RequestParam("bookname") String bookname) { return "resultInsertComment";}

    @GetMapping("/comment/del")
    public String getPageDelete(@RequestParam("bookname") String bookname) { return "deleteComment"; }
}
