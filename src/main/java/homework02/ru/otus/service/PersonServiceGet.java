package ru.otus.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.database.PersonDB;
import ru.otus.domain.Person;

import java.util.Locale;

@Service
public class PersonServiceGet implements PersonService {
    public PersonDB db;
    private MessageSource ms;

    public PersonServiceGet(PersonDB db,MessageSource ms) {
        this.db = db;
        this.ms = ms;
    }

    public Person getPerson() {
        String firstName = ms.getMessage("first.name",null, Locale.ENGLISH);
        String secondName = ms.getMessage("second.name",null,Locale.ENGLISH);
        return db.findPerson(firstName,secondName);
    }
}
