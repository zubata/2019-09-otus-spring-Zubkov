package ru.otus.spring.homework06.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework06.service.BookService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandsForBook {

    private final BookService bookService;

    @ShellMethod(value = "insert book", key = {"inb", "ib"})
    public String insert() {
        String book = bookService.insert();
        if (book==null) return null;
        return String.format("Книга %s добавлена", book);
    }

    @ShellMethod(value = "show book by id", key = "sbi")
    public void showById() {
        bookService.showById();
    }

    @ShellMethod(value = "show book by name", key = "sbn")
    public void showByName() { bookService.showByName(); }

    @ShellMethod(value = "show all book", key = "sab")
    public void showAllRows() {
        bookService.showAllRows();
    }

    @ShellMethod(value = "delete book by Id", key = {"dbi"})
    public String deleteById() {
        String id = bookService.deleteById();
        return String.format("Книга с id %s удалена из БД", id);
    }

    @ShellMethod(value = "delete book by name", key = {"dbn"})
    public String deleteByName() {
        String name = bookService.deleteByName();
        return String.format("Книга с названием %s удалена из БД", name);
    }

    @ShellMethod(value = "show count books", key = "scb")
    public void showCount() {
        bookService.showCount();
    }
}