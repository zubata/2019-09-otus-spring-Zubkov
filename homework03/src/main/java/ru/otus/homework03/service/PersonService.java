package ru.otus.homework03.service;

import ru.otus.homework03.domain.Person;

import java.io.IOException;

public interface PersonService {
    Person getPerson() throws IOException;
}
