package ru.otus.spring.homework11.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.homework11.domain.Author;

@Controller
public class PageAuthorController {

    @GetMapping("/author")
    public String listAuthor() {
        return "authorList";
    }

    @GetMapping("/author/insertauthor")
    public String getPageInsert(Model model) {
        Author form = new Author();
        model.addAttribute("author", form);
        return "insertAuthor";
    }

    @GetMapping("/author/result")
    public String getPageResult() { return "resultInsertAuthor"; }

    @GetMapping("/author/del")
    public String getPageDelete() { return "deleteAuthor"; }

}
