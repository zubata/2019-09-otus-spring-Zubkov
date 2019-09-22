package ru.otus.homework03.database;

import ru.otus.homework03.domain.Person;

public interface PersonDao {
    Person findPerson(String firtsName, String secondName);
}
