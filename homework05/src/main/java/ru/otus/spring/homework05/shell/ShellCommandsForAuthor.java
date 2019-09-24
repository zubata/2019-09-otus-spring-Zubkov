package ru.otus.spring.homework05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework05.service.AuthorService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandsForAuthor {

    private final AuthorService authorService;

    @ShellMethod(value = "insert author", key = {"ina", "ia"})
    public String insert() {
        String author = authorService.insert();
        return String.format("Автор %s добавлен", author);
    }

    @ShellMethod(value = "show author", key = "sha")
    public void showById() {
        authorService.showById();
    }

    @ShellMethod(value = "show all authors", key = "saa")
    public void showAllRows() {
        authorService.showAllRows();
    }

    @ShellMethod(value = "delete author", key = "da")
    public String delete() {
        String id = authorService.delete();
        return String.format("Автор с id %s удален из БД", id);
    }

    @ShellMethod(value = "show count authors", key = "sca")
    public void showCount() {
        authorService.showCount();
    }
}
