package ru.otus.homework04.service;

import ru.otus.homework04.domain.Person;

import java.io.IOException;

public interface PollService {
    void testing() throws IOException;
    void testing(Person person) throws IOException;
}
