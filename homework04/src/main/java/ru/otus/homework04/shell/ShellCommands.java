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

    public ShellCommands(PersonService personService, PollService pollService,MessageWrapper mw) {
        this.pollService=pollService;
        this.personService=personService;
        this.mw = mw;
    }

    @ShellMethod(value = "enter username",key = {"user","start","s"})
    public String fillFullName() throws IOException {
        this.person=personService.getPerson();
        return mw.getMessage("welcome.msg",person.getFirstName(),person.getSecondName());
    }

    @ShellMethod(value = "poll",key = {"p"})
    @ShellMethodAvailability(value = "isPersonAvailable")
    public void testing() throws IOException {
        pollService.testing(person);
    }

    private Availability isPersonAvailable() {
        return person == null? Availability.unavailable("Не введено имя тестируемого"): Availability.available();
    }

}
