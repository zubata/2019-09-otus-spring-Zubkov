package ru.otus.homework03.storage;

import org.springframework.stereotype.Repository;
import ru.otus.homework03.domain.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

    public Person findPerson(String firtsName, String secondName) {
        return new Person(firtsName,secondName);
    }
}
