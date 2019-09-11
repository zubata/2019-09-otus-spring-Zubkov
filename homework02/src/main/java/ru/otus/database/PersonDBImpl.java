package ru.otus.database;

import ru.otus.domain.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDBImpl implements PersonDB {

    public Person findPerson(String firtsName, String secondName) {
        return new Person(firtsName,secondName);
    }
}
