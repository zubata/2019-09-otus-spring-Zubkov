package ru.otus.database;

import org.springframework.stereotype.Repository;
import ru.otus.domain.Person;

@Repository
public class PersonDBImpl implements PersonDB {

    public Person findPerson(String firtsName, String secondName) {
        return new Person(firtsName,secondName);
    }
}
