package ru.otus.project.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.project.service.PersonService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommandsForPerson {
    private final PersonService personService;

    @ShellMethod(value = "set user", key = "su")
    public void setUser() {
        personService.logIn();
    }

    @ShellMethod(value = "set user off", key = "suo")
    public void setUserOff() { personService.logOut(); }

    @ShellMethod(value = "wish vine add", key = "wva")
    public void addFavouriteVine() { personService.addFavouriteVine();}

    @ShellMethod(value = "wish vine delete", key = "wvd")
    public void deleteFavouriteVine() { personService.deleteFavouriteVine();}

    @ShellMethod(value = "wish vine get", key = "wvg")
    public void getFavouriteVines() { personService.getFavouriteVines();}

    @ShellMethod(value = "exit client program", key = "ecp")
    public void exitProgramm() { System.exit(0); }

}
