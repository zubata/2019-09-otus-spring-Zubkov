package ru.otus.spring.homework05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework05.service.BookService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandsForBook {

    private final BookService bookService;

    @ShellMethod(value = "insert book", key = {"inb", "ib"})
    public String insert() {
        String book = bookService.insert();
        return String.format("Книга %s добавлена", book);
    }

    @ShellMethod(value = "show book", key = "shb")
    public void showById() {
        bookService.showById();
    }

    @ShellMethod(value = "show all book", key = "sab")
    public void showAllRows() {
        bookService.showAllRows();
    }

    @ShellMethod(value = "delete book", key = {"db"})
    public String delete() {
        String id = bookService.delete();
        return String.format("Книга с id %s удалена из БД", id);
    }

    @ShellMethod(value = "show count books", key = "scb")
    public void showCount() {
        bookService.showCount();
    }
}