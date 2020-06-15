package ru.otus.project.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.project.service.HistoryService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommandsForHistory {

    private final HistoryService historyService;

    @ShellMethod(value = "get history vine by id", key = "ghvi")
    public void showVineHistoryById() { historyService.getVineHistoryById(); }

    @ShellMethod(value = "get history vine by name", key = "ghvn")
    public void showVineHistoryByName() {
        historyService.getVineHistoryByName();
    }

    @ShellMethod(value = "delete history vine by id", key = "dhvi")
    public void deleteVineHistoryById() {
        historyService.deleteVineHistoryById();
    }

    @ShellMethod(value = "delete history vine all", key = "ghva")
    public void deleteVineHistoryAll() { historyService.deleteAllHistory(); }

}
