package ru.otus.spring.homework08.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework08.service.AuthorService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandsForAuthor {

    private final AuthorService authorService;

    @ShellMethod(value = "insert author", key = {"ina", "ia"})
    public String insert() {
        String author = authorService.insert();
        return String.format("Автор %s добавлен", author);
    }

    @ShellMethod(value = "show author by id", key = "sai")
    public void showById() { authorService.showById(); }

    @ShellMethod(value = "show author by name", key = "san")
    public void showByName() { authorService.showByName(); }

    @ShellMethod(value = "show all authors", key = "saa")
    public void showAllRows() {
        authorService.showAllRows();
    }

    @ShellMethod(value = "delete author by id", key = "dai")
    public String deleteById() {
        String id = authorService.deleteById();
        return String.format("Автор с id %s удален из БД", id);
    }

    @ShellMethod(value = "delete author by name", key = "dan")
    public String deleteByName() {
        String name = authorService.deleteByName();
        return String.format("Автор с именем %s удален из БД", name);
    }

    @ShellMethod(value = "show count authors", key = "sca")
    public void showCount() {
        authorService.showCount();
    }
}
