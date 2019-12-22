package ru.otus.spring.homework15.controllers;

import lombok.Data;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework15.domain.Author;
import ru.otus.spring.homework15.domain.Book;
import ru.otus.spring.homework15.dto.BookDto;
import ru.otus.spring.homework15.integration.GatewayForAuthor;
import ru.otus.spring.homework15.integration.GatewayForBook;
import ru.otus.spring.homework15.security.MyAclService;
import ru.otus.spring.homework15.service.AuthorService;
import ru.otus.spring.homework15.service.BookService;
import ru.otus.spring.homework15.service.BookServiceImpl;

import java.util.List;

@Data
@Controller
public class ControllerForBook {
    private final MyAclService myAclService;
    private final GatewayForBook gatewayBook;
    private final GatewayForAuthor gatewayAuthor;

    @GetMapping("/book/id={id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        Book book = gatewayBook.forFindBook(id);
        model.addAttribute("book", book);
        return "bookList";
    }

    @GetMapping("/book")
    public String getBookAll(Model model) {
        List<Book> books = gatewayBook.forListBook();
        model.addAttribute("book", books);
        model.addAttribute("adminmode", turnAdminMode());
        return "bookList";
    }

    @PostMapping("/book/add")
    public String insertBook(BookDto bookDto, Model model) {
        Book book = gatewayBook.forInsertBook(bookDto);
        myAclService.createACL(book,bookDto.isIsadult());
        model.addAttribute("result", book.getName());
        return "resultInsertBook";
    }


    @GetMapping("/book/insertbook")
    public String getPageInsert(Model model) {
        BookDto form = new BookDto();
        List<Author> authors = gatewayAuthor.forListAuthor();
        model.addAttribute("book", form);
        model.addAttribute("authors", authors);
        return "insertBook";
    }


    @PostMapping("/book/del")
    public String deleteBookById(@RequestParam("id") long id, Model model) {
        String result = gatewayBook.forDeleteBook(id);
        myAclService.removeACL(id);
        model.addAttribute("book", result);
        return "deleteBook";
    }

    private boolean turnAdminMode() {
        try {
            return myAclService.isAdmin();
        } catch (AccessDeniedException e) {
            return false;
        }
    }
}
