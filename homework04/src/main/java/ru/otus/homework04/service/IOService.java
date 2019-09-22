package ru.otus.homework04.service;

import java.io.IOException;

public interface IOService {

    void output(String s);
    String input() throws IOException;

}
