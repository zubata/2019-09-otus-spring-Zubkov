package ru.otus.homework04.storage;

import ru.otus.homework04.domain.Person;

public interface PersonDao {
    Person findPerson(String firtsName, String secondName);
}
