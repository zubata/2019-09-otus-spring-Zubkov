package ru.otus.homework03.service;

import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.Person;
import ru.otus.homework03.config.MessageWrapper;
import ru.otus.homework03.database.PersonDao;

import java.io.IOException;

@Service
public class PersonServiceImpl implements PersonService {
    private final IOService IOService;
    private final MessageWrapper mw;
    private PersonDao db;

    public PersonServiceImpl(PersonDao db, MessageWrapper mw , IOService IOService) {
        this.db = db;
        this.mw = mw;
        this.IOService = IOService;
    }

    public Person getPerson() throws IOException {
        IOService.output(mw.getMessage("input.first.name"));
        String firstName = IOService.input();
        IOService.output(mw.getMessage("input.second.name"));
        String secondName = IOService.input();
        return db.findPerson(firstName,secondName);
    }
}
