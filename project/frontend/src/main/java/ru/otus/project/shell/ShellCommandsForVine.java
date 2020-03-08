package ru.otus.project.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.project.config.IOService;
import ru.otus.project.service.BatchService;
import ru.otus.project.service.ParseSiteService;
import ru.otus.project.service.VineService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommandsForVine {

    private final VineService vineService;

    @ShellMethod(value = "insert vine", key = "iv")
    public void insertVine() {
        vineService.insert();
    }

    @ShellMethod(value = "get vines all", key = "gva")
    public void getAllVineList() { vineService.getList(); }

    @ShellMethod(value = "get vine by name", key = "gvn")
    public void getVineByName() { vineService.getByName();}

    @ShellMethod(value = "get vine by id", key = "gvi")
    public void getVineById() { vineService.getById();}

    @ShellMethod(value = "delete vine by id", key = "dvi")
    public void deleteById() { vineService.deleteById(); }

    @ShellMethod(value = "delete vine all", key = "dva")
    public void deleteAllVine() { vineService.deleteAll(); }

}
