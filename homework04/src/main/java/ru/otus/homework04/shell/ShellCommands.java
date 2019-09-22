package ru.otus.homework04.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homework04.config.MessageWrapper;
import ru.otus.homework04.domain.Person;
import ru.otus.homework04.service.PersonService;
import ru.otus.homework04.service.PollService;

import java.io.IOException;

@ShellComponent
public class ShellCommands {

    private final PollService pollService;
    private final PersonService personService;
    private final MessageWrapper mw;

    private Person person;
    private boolean isStart=false;

    public ShellCommands(PersonService personService, PollService pollService,MessageWrapper mw) {
        this.pollService=pollService;
        this.personService=personService;
        this.mw = mw;
    }

    @ShellMethod(value = "start",key = {"start","s"})
    public String start() throws IOException {
        isStart=true;
        return mw.getMessage("welcome.msg");
    }

    @ShellMethod(value = "enter username",key = {"enter","e"})
    public String fillFullName() throws IOException {
        this.person=personService.getPerson();
        return mw.getMessage("welcome.user.msg",person.getFirstName(),person.getSecondName());
    }

    @ShellMethod(value = "poll",key = {"p"})
    @ShellMethodAvailability(value = "isPersonAvailable")
    public void testing() throws IOException {
        pollService.testing(person);
    }

    private Availability isPersonAvailable() {
        return person == null? Availability.unavailable(mw.getMessage("error.user.msg")): Availability.available();
    }
}
