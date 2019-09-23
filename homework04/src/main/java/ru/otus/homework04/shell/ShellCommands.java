package ru.otus.homework04.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homework04.config.MessageWrapper;
import ru.otus.homework04.domain.Person;
import ru.otus.homework04.service.PersonService;
import ru.otus.homework04.service.PollService;

@ShellComponent
public class ShellCommands {

    private final PollService pollService;
    private final PersonService personService;
    private final MessageWrapper mw;

    private Person person;
    private boolean isStart = false;

    public ShellCommands(PersonService personService, PollService pollService, MessageWrapper mw) {
        this.pollService = pollService;
        this.personService = personService;
        this.mw = mw;
    }

    @ShellMethod(value = "start", key = {"start", "s"})
    public String start() {
        isStart = true;
        return mw.getMessage("welcome.msg");
    }

    @ShellMethod(value = "enter username", key = {"enter", "e"})
    @ShellMethodAvailability(value = "isAppStart")
    public String fillFullName() {
        this.person = personService.getPerson();
        return mw.getMessage("welcome.user.msg", person.getFirstName(), person.getSecondName());
    }

    @ShellMethod(value = "poll", key = {"p"})
    @ShellMethodAvailability(value = "isPersonAvailable")
    public void testing() {
        pollService.testing(person);
    }

    private Availability isPersonAvailable() {
        if (!isStart) return isAppStart();
        return person == null ? Availability.unavailable(mw.getMessage("error.user.msg")) : Availability.available();
    }

    private Availability isAppStart() {
        return isStart ? Availability.available() : Availability.unavailable(mw.getMessage("error.app.msg"));
    }
}
