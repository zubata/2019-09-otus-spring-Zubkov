package ru.otus.spring.homework05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework05.service.GenreService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandsForGenre {

    private final GenreService genreService;

    @ShellMethod(value = "insert genre", key = {"ing", "ig"})
    public String insert() {
        String genre = genreService.insert();
        return String.format("Жанр %s добавлен", genre);
    }

    @ShellMethod(value = "show genre", key = "shg")
    public void showById() {
        genreService.showById();
    }

    @ShellMethod(value = "show all genres", key = "sag")
    public void showAllRows() {
        genreService.showAllRows();
    }

    @ShellMethod(value = "delete genre", key = "dg")
    public String delete() {
        String id = genreService.delete();
        return String.format("Жанр с id %s удалён из БД", id);
    }

    @ShellMethod(value = "show count genres", key = "scg")
    public void showCount() {
        genreService.showCount();
    }
}
