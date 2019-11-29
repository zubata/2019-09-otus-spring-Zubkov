package ru.otus.spring.homework12.controllers;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework12.domain.Author;
import ru.otus.spring.homework12.domain.Book;
import ru.otus.spring.homework12.dto.BookDto;
import ru.otus.spring.homework12.service.AuthorService;
import ru.otus.spring.homework12.service.BookService;

import java.util.List;

@Data
@Controller
public class ControllerForBook {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/book/id={id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        Book book = bookService.showById(id);
        model.addAttribute("book", book);
        return "bookList";
    }

    @GetMapping("/book")
    public String getBookAll(Model model) {
        List<Book> books = bookService.showAllRows();
        model.addAttribute("book", books);
        return "bookList";
    }

    @PostMapping("/book/add")
    public String insertBook(BookDto bookDto, Model model) {
        String result = bookService.insert(bookDto);
        model.addAttribute("result", result);
        return "resultInsertBook";
    }


    @GetMapping("/book/insertbook")
    public String getPageInsert(Model model) {
        BookDto form = new BookDto();
        List<Author> authors = authorService.showAllRows();
        model.addAttribute("book", form);
        model.addAttribute("authors", authors);
        return "insertBook";
    }


    @PostMapping("/book/del")
    public String deleteBookById(@RequestParam("id") long id, Model model) {
        String result = bookService.deleteById(id);
        model.addAttribute("book", result);
        return "deleteBook";
    }
}
