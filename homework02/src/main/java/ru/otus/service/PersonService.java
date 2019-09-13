package ru.otus.service;

import ru.otus.domain.Person;

import java.io.IOException;

public interface PersonService {
    Person getPerson() throws IOException;
}
