package ru.otus.homework04.database;

import org.springframework.stereotype.Repository;
import ru.otus.homework04.domain.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

    public Person findPerson(String firtsName, String secondName) {
        return new Person(firtsName,secondName);
    }
}
