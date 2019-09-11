package ru.otus.database;

import ru.otus.domain.Person;

public interface PersonDB {
    Person findPerson(String firtsName, String secondName);
}
