package homework02.ru.otus.database;

import homework02.ru.otus.domain.Person;

public interface PersonDB {
    Person findPerson(String firtsName, String secondName);
}
