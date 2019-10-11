package ru.otus.spring.homework07.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework07.service.GenreService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandsForGenre {

    private final GenreService genreService;

    @ShellMethod(value = "insert genre", key = {"ing", "ig"})
    public String insert() {
        String genre = genreService.insert();
        return String.format("Жанр %s добавлен", genre);
    }

    @ShellMethod(value = "show genre by id", key = "sgi")
    public void showById() {
        genreService.showById();
    }

    @ShellMethod(value = "show genre by name", key = "sgn")
    public void showByName() {
        genreService.showByName();
    }

    @ShellMethod(value = "show all genres", key = "sag")
    public void showAllRows() {
        genreService.showAllRows();
    }

    @ShellMethod(value = "delete genre by id", key = "dgi")
    public String deleteById() {
        String id = genreService.deleteById();
        return String.format("Жанр с id %s удалён из БД", id);
    }

    @ShellMethod(value = "delete genre by name", key = "dgn")
    public String deleteByName() {
        String name = genreService.deleteByName();
        return String.format("Жанр с названием %s удалён из БД", name);
    }

    @ShellMethod(value = "show count genres", key = "scg")
    public void showCount() {
        genreService.showCount();
    }
}
