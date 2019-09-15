package ru.otus.service;

import ru.otus.config.MessageWrapper;
import ru.otus.domain.Person;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.database.PersonDao;

import java.io.IOException;
import java.util.Locale;

@Service
public class PersonServiceImpl implements PersonService {
    private final InOutService inOutService;
    private final MessageWrapper mw;
    private PersonDao db;

    public PersonServiceImpl(PersonDao db, MessageWrapper mw , InOutService inOutService) {
        this.db = db;
        this.mw = mw;
        this.inOutService=inOutService;
    }

    public Person getPerson() throws IOException {
        inOutService.output(mw.getMessage("input.first.name"));
        String firstName = inOutService.input();
        inOutService.output(mw.getMessage("input.second.name"));
        String secondName = inOutService.input();
        return db.findPerson(firstName,secondName);
    }
}
