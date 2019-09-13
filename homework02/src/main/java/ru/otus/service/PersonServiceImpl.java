package ru.otus.service;

import ru.otus.domain.Person;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.database.PersonDao;

import java.io.IOException;
import java.util.Locale;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonDao db;
    private InOutService inOutService;
    private MessageSource ms;

    public PersonServiceImpl(PersonDao db, MessageSource ms , InOutService inOutService) {
        this.db = db;
        this.ms = ms;
        this.inOutService=inOutService;
    }

    public Person getPerson() throws IOException {
        inOutService.output(ms.getMessage("input.first.name",null,Locale.getDefault()));
        String firstName = inOutService.input();
        inOutService.output(ms.getMessage("input.second.name",null,Locale.getDefault()));
        String secondName = inOutService.input();
        return db.findPerson(firstName,secondName);
    }
}
