package ru.otus.database;

import ru.otus.domain.Person;

public interface PersonDao {
    Person findPerson(String firtsName, String secondName);
}
