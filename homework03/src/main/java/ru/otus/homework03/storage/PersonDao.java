package ru.otus.homework03.storage;

import ru.otus.homework03.domain.Person;

public interface PersonDao {
    Person findPerson(String firtsName, String secondName);
}
