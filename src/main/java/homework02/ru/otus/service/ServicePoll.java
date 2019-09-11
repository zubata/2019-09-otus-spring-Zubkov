package ru.otus.service;

import ru.otus.domain.Person;

import java.io.IOException;

public interface ServicePoll {
    void testing(Person person) throws IOException;
}
