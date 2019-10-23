package ru.otus.spring.homework08.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework08.service.GenreService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandsForGenre {

    private final GenreService genreService;

    @ShellMethod(value = "update genre by name", key = "ugn")
    public String updateGenre() { return genreService.updateGenre(); }

    @ShellMethod(value = "delete genre by name", key = {"dgn"})
    public String deleteGenre() {
        return genreService.deleteGenre();
    }

}
